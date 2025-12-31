package com.scrcpy.gui.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
//import java.nio.file.*;
/**
 * Manages saving and loading scrcpy configurations
 */
public class ConfigurationManager {
    private static final String CONFIG_DIR = System.getProperty("user.home") + File.separator + ".scrcpy-gui";
    private static final String DEFAULT_CONFIG_FILE = "default.json";
    private final Gson gson;

    public ConfigurationManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        ensureConfigDir();
    }

    private void ensureConfigDir() {
        File dir = new File(CONFIG_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Save configuration to a file
     */
    public void saveConfig(ScrcpyConfig config, String profileName) throws IOException {
        String filename = profileName.endsWith(".json") ? profileName : profileName + ".json";
        File file = new File(CONFIG_DIR, filename);
        
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(config, writer);
        }
    }

    /**
     * Save configuration to default profile
     */
    public void saveDefaultConfig(ScrcpyConfig config) throws IOException {
        saveConfig(config, DEFAULT_CONFIG_FILE);
    }

    /**
     * Load configuration from a file
     */
    public ScrcpyConfig loadConfig(String profileName) throws IOException {
        String filename = profileName.endsWith(".json") ? profileName : profileName + ".json";
        File file = new File(CONFIG_DIR, filename);
        
        if (!file.exists()) {
            return new ScrcpyConfig();
        }

        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, ScrcpyConfig.class);
        }
    }

    /**
     * Load default configuration
     */
    public ScrcpyConfig loadDefaultConfig() {
        try {
            return loadConfig(DEFAULT_CONFIG_FILE);
        } catch (IOException e) {
            return new ScrcpyConfig();
        }
    }

    /**
     * List all saved configuration profiles
     */
    public String[] listProfiles() {
        File dir = new File(CONFIG_DIR);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
        
        if (files == null || files.length == 0) {
            return new String[0];
        }

        String[] profiles = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            profiles[i] = name.substring(0, name.length() - 5); // Remove .json
        }
        return profiles;
    }

    /**
     * Delete a configuration profile
     */
    public boolean deleteProfile(String profileName) {
        String filename = profileName.endsWith(".json") ? profileName : profileName + ".json";
        File file = new File(CONFIG_DIR, filename);
        return file.exists() && file.delete();
    }

    /**
     * Get the configuration directory path
     */
    public String getConfigDirectory() {
        return CONFIG_DIR;
    }
}
