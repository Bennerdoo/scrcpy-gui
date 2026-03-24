package com.scrcpy.gui.util;

import java.io.*;
import java.nio.file.*;

/**
 * Utility class to handle bundled scrcpy resources
 */
public class ScrcpyResourceManager {
    private static final String SCRCPY_DIR = "scrcpy";
    private static Path extractedPath = null;

    /**
     * Get the path to the scrcpy executable.
     * First tries to use bundled resources, falls back to system PATH.
     */
    public static String getScrcpyExecutable() {
        // Try bundled version first
        String bundledPath = extractBundledScrcpy();
        if (bundledPath != null) {
            return bundledPath;
        }

        // Fall back to system PATH
        return "scrcpy";
    }

    /**
     * Get the path to the adb executable.
     * First tries to use bundled resources, falls back to system PATH.
     */
    public static String getAdbExecutable() {
        if (extractedPath != null && Files.exists(extractedPath.resolve("adb.exe"))) {
            return extractedPath.resolve("adb.exe").toString();
        }
        
        // Fall back to system PATH
        return "adb";
    }

    /**
     * Extract bundled scrcpy resources to a temporary directory
     */
    private static String extractBundledScrcpy() {
        if (extractedPath != null && Files.exists(extractedPath.resolve("scrcpy.exe"))) {
            return extractedPath.resolve("scrcpy.exe").toString();
        }

        try {
            // Check if running from JAR with bundled resources
            InputStream testStream = ScrcpyResourceManager.class.getResourceAsStream("/" + SCRCPY_DIR + "/scrcpy.exe");
            if (testStream == null) {
                // Not running from JAR or resources not bundled
                return null;
            }
            testStream.close();

            // Create temporary directory for extraction
            extractedPath = Files.createTempDirectory("scrcpy-gui-");
            extractedPath.toFile().deleteOnExit();

            // Extract all scrcpy resources
            extractResource("/" + SCRCPY_DIR + "/scrcpy.exe", extractedPath.resolve("scrcpy.exe"));
            extractResource("/" + SCRCPY_DIR + "/scrcpy-server", extractedPath.resolve("scrcpy-server"));
            extractResource("/" + SCRCPY_DIR + "/SDL2.dll", extractedPath.resolve("SDL2.dll"));
            extractResource("/" + SCRCPY_DIR + "/avcodec-61.dll", extractedPath.resolve("avcodec-61.dll"));
            extractResource("/" + SCRCPY_DIR + "/avformat-61.dll", extractedPath.resolve("avformat-61.dll"));
            extractResource("/" + SCRCPY_DIR + "/avutil-59.dll", extractedPath.resolve("avutil-59.dll"));
            extractResource("/" + SCRCPY_DIR + "/swresample-5.dll", extractedPath.resolve("swresample-5.dll"));
            extractResource("/" + SCRCPY_DIR + "/adb.exe", extractedPath.resolve("adb.exe"));
            extractResource("/" + SCRCPY_DIR + "/AdbWinApi.dll", extractedPath.resolve("AdbWinApi.dll"));
            extractResource("/" + SCRCPY_DIR + "/AdbWinUsbApi.dll", extractedPath.resolve("AdbWinUsbApi.dll"));
            extractResource("/" + SCRCPY_DIR + "/libusb-1.0.dll", extractedPath.resolve("libusb-1.0.dll"));

            String exePath = extractedPath.resolve("scrcpy.exe").toString();
            System.out.println("Using bundled scrcpy from: " + exePath);
            return exePath;

        } catch (IOException e) {
            System.err.println("Failed to extract bundled scrcpy: " + e.getMessage());
            return null;
        }
    }

    /**
     * Extract a single resource file
     */
    private static void extractResource(String resourcePath, Path targetPath) throws IOException {
        try (InputStream in = ScrcpyResourceManager.class.getResourceAsStream(resourcePath)) {
            if (in == null) {
                // Resource doesn't exist, skip it
                return;
            }

            Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);

            // Make executable on Unix-like systems
            File file = targetPath.toFile();
            file.setExecutable(true);
            file.deleteOnExit();
        }
    }

    /**
     * Check if bundled scrcpy is available
     */
    public static boolean isBundledScrcpyAvailable() {
        InputStream testStream = ScrcpyResourceManager.class.getResourceAsStream("/" + SCRCPY_DIR + "/scrcpy.exe");
        if (testStream != null) {
            try {
                testStream.close();
                return true;
            } catch (IOException e) {
                // Ignore
            }
        }
        return false;
    }
}
