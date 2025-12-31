package com.scrcpy.gui;

import com.scrcpy.gui.config.ConfigurationManager;
import com.scrcpy.gui.config.ScrcpyConfig;
import com.scrcpy.gui.core.CommandBuilder;
import com.scrcpy.gui.core.ProcessExecutor;
import com.scrcpy.gui.panels.AllOptionsPanel;
import com.scrcpy.gui.panels.ConnectionPanel;
import com.scrcpy.gui.panels.VideoPanel;

import javax.swing.*;
import java.awt.*;
//import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Main GUI application for scrcpy
 */
public class ScrcpyGUI extends JFrame {
    private static final String VERSION = "1.1.0";

    // Panels
    private ConnectionPanel connectionPanel;
    private VideoPanel videoPanel;
    private AllOptionsPanel allOptionsPanel;

    // Output
    private JTextArea commandTextArea;
    private JTextArea outputTextArea;

    // Control buttons
    private JButton startButton;
    private JButton stopButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton resetButton;

    // Core components
    private ConfigurationManager configManager;
    private ProcessExecutor processExecutor;
    private ScrcpyConfig currentConfig;

    public ScrcpyGUI() {
        super("Scrcpy GUI v" + VERSION);

        configManager = new ConfigurationManager();
        currentConfig = configManager.loadDefaultConfig();

        initializeComponents();
        setupLayout();
        setupMenuBar();
        setupListeners();
        loadConfigToUI();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Set custom app icon
        setAppIcon();
    }

    private void setAppIcon() {
        try {
            // Try to load icon from resources
            java.net.URL iconURL = getClass().getClassLoader().getResource("app-icon.png");
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                setIconImage(icon.getImage());
            }
        } catch (Exception e) {
            // If icon loading fails, just use default Java icon
            System.err.println("Could not load app icon: " + e.getMessage());
        }
    }

    private void initializeComponents() {
        // Create panels
        connectionPanel = new ConnectionPanel();
        videoPanel = new VideoPanel();
        allOptionsPanel = new AllOptionsPanel();

        // Create command display
        commandTextArea = new JTextArea(3, 50);
        commandTextArea.setEditable(false);
        commandTextArea.setLineWrap(true);
        commandTextArea.setWrapStyleWord(true);
        commandTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        commandTextArea.setBorder(BorderFactory.createTitledBorder("Generated Command"));

        // Create output display
        outputTextArea = new JTextArea(10, 50);
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        outputTextArea.setBorder(BorderFactory.createTitledBorder("Output"));

        // Create buttons
        startButton = new JButton("Start scrcpy");
        startButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        startButton.setForeground(new Color(0, 128, 0));

        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        stopButton.setForeground(new Color(192, 0, 0));

        saveButton = new JButton("Save Config");
        loadButton = new JButton("Load Config");
        resetButton = new JButton("Reset to Defaults");
    }

    private void setupLayout() {
        setLayout(new BorderLayout(5, 5));

        // Create tabbed pane for options
        JTabbedPane optionsTabs = new JTabbedPane();
        optionsTabs.addTab("Connection", new JScrollPane(connectionPanel));
        optionsTabs.addTab("Video", new JScrollPane(videoPanel));
        optionsTabs.addTab("All Options", new JScrollPane(allOptionsPanel));

        // Create center panel with options tabs
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(optionsTabs, BorderLayout.CENTER);

        // Create bottom panel with command, output, and buttons
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Command panel
        JPanel commandPanel = new JPanel(new BorderLayout());
        commandPanel.add(new JScrollPane(commandTextArea), BorderLayout.CENTER);

        // Output panel
        JPanel outputPanel = new JPanel(new BorderLayout());
        JScrollPane outputScroll = new JScrollPane(outputTextArea);
        outputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        outputPanel.add(outputScroll, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonsPanel.add(startButton);
        buttonsPanel.add(stopButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(resetButton);

        // Combine bottom components
        JPanel displayPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        displayPanel.add(commandPanel);
        displayPanel.add(outputPanel);

        bottomPanel.add(displayPanel, BorderLayout.CENTER);

        // Buttons and signature panel container
        JPanel buttonsContainer = new JPanel(new BorderLayout());
        buttonsContainer.add(buttonsPanel, BorderLayout.CENTER);

        // Signature label
        JLabel signatureLabel = new JLabel("Made with Love by Bennerdoo ❤", SwingConstants.CENTER);
        signatureLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 10));
        signatureLabel.setForeground(new Color(128, 128, 128));
        signatureLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        buttonsContainer.add(signatureLabel, BorderLayout.SOUTH);

        bottomPanel.add(buttonsContainer, BorderLayout.SOUTH);

        // Add to main frame
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveConfigItem = new JMenuItem("Save Configuration...");
        saveConfigItem.addActionListener(e -> saveConfiguration());

        JMenuItem loadConfigItem = new JMenuItem("Load Configuration...");
        loadConfigItem.addActionListener(e -> loadConfiguration());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(saveConfigItem);
        fileMenu.add(loadConfigItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAboutDialog());

        JMenuItem scrcpyHelpItem = new JMenuItem("scrcpy Documentation");
        scrcpyHelpItem.addActionListener(e -> {
            String message = "For detailed scrcpy documentation, visit:\n" +
                    "https://github.com/Genymobile/scrcpy\n\n" +
                    "Or run: scrcpy --help";
            JOptionPane.showMessageDialog(this, message, "scrcpy Documentation",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        helpMenu.add(scrcpyHelpItem);
        helpMenu.addSeparator();
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    private void setupListeners() {
        // Start button
        startButton.addActionListener(e -> startScrcpy());

        // Stop button
        stopButton.addActionListener(e -> stopScrcpy());

        // Save button
        saveButton.addActionListener(e -> saveConfiguration());

        // Load button
        loadButton.addActionListener(e -> loadConfiguration());

        // Reset button
        resetButton.addActionListener(e -> resetToDefaults());

        // Update command on any option change
        Timer updateTimer = new Timer(500, e -> updateCommandDisplay());
        updateTimer.setRepeats(false);
        updateTimer.start();
    }

    private void loadConfigToUI() {
        connectionPanel.loadConfig(currentConfig);
        videoPanel.loadConfig(currentConfig);
        allOptionsPanel.loadConfig(currentConfig);
        updateCommandDisplay();
    }

    private void saveConfigFromUI() {
        connectionPanel.saveConfig(currentConfig);
        videoPanel.saveConfig(currentConfig);
        allOptionsPanel.saveConfig(currentConfig);
    }

    private void updateCommandDisplay() {
        saveConfigFromUI();
        CommandBuilder builder = new CommandBuilder(currentConfig);
        String command = builder.buildCommandString();
        commandTextArea.setText(command);
    }

    private void startScrcpy() {
        if (processExecutor != null && processExecutor.isRunning()) {
            JOptionPane.showMessageDialog(this,
                    "scrcpy is already running!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        saveConfigFromUI();
        updateCommandDisplay();

        outputTextArea.setText("");
        outputTextArea.append("Starting scrcpy...\n");

        CommandBuilder builder = new CommandBuilder(currentConfig);
        List<String> command = builder.buildCommand();

        processExecutor = new ProcessExecutor(
                output -> SwingUtilities.invokeLater(() -> {
                    outputTextArea.append(output);
                    outputTextArea.setCaretPosition(outputTextArea.getDocument().getLength());
                }),
                error -> SwingUtilities.invokeLater(() -> {
                    outputTextArea.append("[ERROR] " + error);
                    outputTextArea.setCaretPosition(outputTextArea.getDocument().getLength());
                }));

        try {
            processExecutor.start(command);
            startButton.setEnabled(false);
            stopButton.setEnabled(true);

            // Auto-save on successful start
            try {
                configManager.saveDefaultConfig(currentConfig);
            } catch (IOException ex) {
                // Silently fail
            }

        } catch (IOException ex) {
            outputTextArea.append("\n[FAILED] Error starting scrcpy: " + ex.getMessage() + "\n");
            outputTextArea.append("Make sure scrcpy is installed and available in PATH.\n");
            JOptionPane.showMessageDialog(this,
                    "Failed to start scrcpy:\n" + ex.getMessage() +
                            "\n\nMake sure scrcpy is installed and available in your system PATH.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }

        // Monitor process completion
        new Thread(() -> {
            while (processExecutor != null && processExecutor.isRunning()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
            }
            SwingUtilities.invokeLater(() -> {
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            });
        }).start();
    }

    private void stopScrcpy() {
        if (processExecutor != null && processExecutor.isRunning()) {
            outputTextArea.append("\nStopping scrcpy...\n");
            processExecutor.stop();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }

    private void saveConfiguration() {
        String profileName = JOptionPane.showInputDialog(this,
                "Enter configuration name:",
                "Save Configuration",
                JOptionPane.QUESTION_MESSAGE);

        if (profileName != null && !profileName.trim().isEmpty()) {
            saveConfigFromUI();
            try {
                configManager.saveConfig(currentConfig, profileName);
                JOptionPane.showMessageDialog(this,
                        "Configuration saved successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Failed to save configuration:\n" + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadConfiguration() {
        String[] profiles = configManager.listProfiles();

        if (profiles.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "No saved configurations found.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String selected = (String) JOptionPane.showInputDialog(this,
                "Select configuration to load:",
                "Load Configuration",
                JOptionPane.QUESTION_MESSAGE,
                null,
                profiles,
                profiles[0]);

        if (selected != null) {
            try {
                currentConfig = configManager.loadConfig(selected);
                loadConfigToUI();
                JOptionPane.showMessageDialog(this,
                        "Configuration loaded successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Failed to load configuration:\n" + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetToDefaults() {
        int result = JOptionPane.showConfirmDialog(this,
                "Reset all options to default values?",
                "Confirm Reset",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            currentConfig = new ScrcpyConfig();
            loadConfigToUI();
        }
    }

    private void showAboutDialog() {
        String message = "Scrcpy GUI v" + VERSION + "\n\n" +
                "A graphical user interface for scrcpy\n" +
                "https://github.com/Genymobile/scrcpy\n\n" +
                "This GUI provides easy access to all scrcpy\n" +
                "command-line options through an intuitive interface.\n\n" +
                "Configurations are saved to:\n" +
                configManager.getConfigDirectory() + "\n\n" +
                "━━━━━━━━━━━━━━━━━━━━━\n" +
                "Made with Love by Bennerdoo ❤";

        JOptionPane.showMessageDialog(this,
                message,
                "About Scrcpy GUI",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Use default look and feel
        }

        // Create and show GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            ScrcpyGUI gui = new ScrcpyGUI();
            gui.setVisible(true);
        });
    }
}
