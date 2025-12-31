package com.scrcpy.gui.panels;

import com.scrcpy.gui.config.ScrcpyConfig;

import javax.swing.*;
import java.awt.*;

/**
 * Comprehensive panel for all other scrcpy options
 */
public class AllOptionsPanel extends JPanel {
    private JTabbedPane tabbedPane;
    
    // Audio controls
    private JComboBox<String> audioSourceCombo;
    private JComboBox<String> audioCodecCombo;
    private JTextField audioBitRateField;
    private JCheckBox noAudioCheck;
    
    // Control options
    private JCheckBox noControlCheck;
    private JCheckBox legacyPasteCheck;
    private JCheckBox preferTextCheck;
    
    // Input options
    private JComboBox<String> keyboardModeCombo;
    private JComboBox<String> mouseModeCombo;
    private JComboBox<String> gamepadModeCombo;
    private JCheckBox otgCheck;
    
    // Device options
    private JCheckBox showTouchesCheck;
    private JCheckBox stayAwakeCheck;
    private JCheckBox turnScreenOffCheck;
    
    // Window options
    private JTextField windowTitleField;
    private JCheckBox fullscreenCheck;
    private JCheckBox alwaysOnTopCheck;
    private JCheckBox windowBorderlessCheck;
    
    // Recording options
    private JTextField recordFileField;
    private JComboBox<String> recordFormatCombo;
    private JComboBox<String> recordOrientationCombo;
    
    // Camera options
    private JComboBox<String> cameraFacingCombo;
    private JTextField cameraSizeField;
    private JTextField cameraFpsField;
    
    // Advanced options
    private JComboBox<String> verbosityCombo;
    private JCheckBox printFpsCheck;

    public AllOptionsPanel() {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        
        // Create sub-panels
        tabbedPane.addTab("Audio", createAudioPanel());
        tabbedPane.addTab("Control", createControlPanel());
        tabbedPane.addTab("Input", createInputPanel());
        tabbedPane.addTab("Device", createDevicePanel());
        tabbedPane.addTab("Window", createWindowPanel());
        tabbedPane.addTab("Recording", createRecordingPanel());
        tabbedPane.addTab("Camera", createCameraPanel());
        tabbedPane.addTab("Advanced", createAdvancedPanel());
        
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createAudioPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Audio Source:"), gbc);
        gbc.gridx = 1;
        audioSourceCombo = new JComboBox<>(new String[]{
            "output", "playback", "mic", "mic-unprocessed", "mic-camcorder"
        });
        panel.add(audioSourceCombo, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Audio Codec:"), gbc);
        gbc.gridx = 1;
        audioCodecCombo = new JComboBox<>(new String[]{"opus", "aac", "flac", "raw"});
        panel.add(audioCodecCombo, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Audio Bit Rate:"), gbc);
        gbc.gridx = 1;
        audioBitRateField = new JTextField("128K", 20);
        panel.add(audioBitRateField, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        noAudioCheck = new JCheckBox("Disable Audio (--no-audio)");
        panel.add(noAudioCheck, gbc);

        // Add filler
        gbc.gridx = 0; gbc.gridy = ++row; gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;

        int row = 0;

        gbc.gridy = row++;
        noControlCheck = new JCheckBox("Disable Control (--no-control)");
        noControlCheck.setToolTipText("Mirror device in read-only mode");
        panel.add(noControlCheck, gbc);

        gbc.gridy = row++;
        legacyPasteCheck = new JCheckBox("Legacy Paste (--legacy-paste)");
        panel.add(legacyPasteCheck, gbc);

        gbc.gridy = row++;
        preferTextCheck = new JCheckBox("Prefer Text (--prefer-text)");
        panel.add(preferTextCheck, gbc);

        // Add filler
        gbc.gridy = row; gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Keyboard Mode:"), gbc);
        gbc.gridx = 1;
        keyboardModeCombo = new JComboBox<>(new String[]{"", "disabled", "sdk", "uhid", "aoa"});
        panel.add(keyboardModeCombo, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Mouse Mode:"), gbc);
        gbc.gridx = 1;
        mouseModeCombo = new JComboBox<>(new String[]{"", "disabled", "sdk", "uhid", "aoa"});
        panel.add(mouseModeCombo, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Gamepad Mode:"), gbc);
        gbc.gridx = 1;
        gamepadModeCombo = new JComboBox<>(new String[]{"", "disabled", "uhid", "aoa"});
        panel.add(gamepadModeCombo, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        otgCheck = new JCheckBox("OTG Mode (--otg)");
        otgCheck.setToolTipText("Simulate physical keyboard/mouse (no mirroring)");
        panel.add(otgCheck, gbc);

        // Add filler
        gbc.gridx = 0; gbc.gridy = ++row; gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    private JPanel createDevicePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;

        int row = 0;

        gbc.gridy = row++;
        showTouchesCheck = new JCheckBox("Show Touches (-t, --show-touches)");
        panel.add(showTouchesCheck, gbc);

        gbc.gridy = row++;
        stayAwakeCheck = new JCheckBox("Stay Awake (-w, --stay-awake)");
        panel.add(stayAwakeCheck, gbc);

        gbc.gridy = row++;
        turnScreenOffCheck = new JCheckBox("Turn Screen Off (-S, --turn-screen-off)");
        panel.add(turnScreenOffCheck, gbc);

        // Add filler
        gbc.gridy = row; gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    private JPanel createWindowPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(new JLabel("Window Title:"), gbc);
        gbc.gridx = 1;
        windowTitleField = new JTextField(20);
        panel.add(windowTitleField, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        fullscreenCheck = new JCheckBox("Fullscreen (-f, --fullscreen)");
        panel.add(fullscreenCheck, gbc);

        row++;

        gbc.gridy = row++;
        alwaysOnTopCheck = new JCheckBox("Always on Top (--always-on-top)");
        panel.add(alwaysOnTopCheck, gbc);

        gbc.gridy = row++;
        windowBorderlessCheck = new JCheckBox("Borderless Window (--window-borderless)");
        panel.add(windowBorderlessCheck, gbc);

        // Add filler
        gbc.gridy = row; gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    private JPanel createRecordingPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(new JLabel("Record File (-r):"), gbc);
        gbc.gridx = 1;
        recordFileField = new JTextField(20);
        recordFileField.setToolTipText("File path to save recording");
        panel.add(recordFileField, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Record Format:"), gbc);
        gbc.gridx = 1;
        recordFormatCombo = new JComboBox<>(new String[]{"", "mp4", "mkv", "m4a", "mka", "opus", "aac", "flac", "wav"});
        panel.add(recordFormatCombo, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Record Orientation:"), gbc);
        gbc.gridx = 1;
        recordOrientationCombo = new JComboBox<>(new String[]{"0", "90", "180", "270"});
        panel.add(recordOrientationCombo, gbc);

        // Add filler
        gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2; gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    private JPanel createCameraPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(new JLabel("Camera Facing:"), gbc);
        gbc.gridx = 1;
        cameraFacingCombo = new JComboBox<>(new String[]{"", "front", "back", "external"});
        panel.add(cameraFacingCombo, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Camera Size:"), gbc);
        gbc.gridx = 1;
        cameraSizeField = new JTextField(20);
        cameraSizeField.setToolTipText("e.g., 1920x1080");
        panel.add(cameraSizeField, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Camera FPS:"), gbc);
        gbc.gridx = 1;
        cameraFpsField = new JTextField(20);
        panel.add(cameraFpsField, gbc);

        // Add filler
        gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2; gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    private JPanel createAdvancedPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(new JLabel("Verbosity (-V):"), gbc);
        gbc.gridx = 1;
        verbosityCombo = new JComboBox<>(new String[]{"info", "verbose", "debug", "warn", "error"});
        panel.add(verbosityCombo, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        printFpsCheck = new JCheckBox("Print FPS (--print-fps)");
        panel.add(printFpsCheck, gbc);

        // Add filler
        gbc.gridx = 0; gbc.gridy = ++row; gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    public void loadConfig(ScrcpyConfig config) {
        // Audio
        audioSourceCombo.setSelectedItem(config.getAudioSource());
        audioCodecCombo.setSelectedItem(config.getAudioCodec());
        audioBitRateField.setText(config.getAudioBitRate());
        noAudioCheck.setSelected(config.isNoAudio());
        
        // Control
        noControlCheck.setSelected(config.isNoControl());
        legacyPasteCheck.setSelected(config.isLegacyPaste());
        preferTextCheck.setSelected(config.isPreferText());
        
        // Input
        keyboardModeCombo.setSelectedItem(config.getKeyboardMode() != null ? config.getKeyboardMode() : "");
        mouseModeCombo.setSelectedItem(config.getMouseMode() != null ? config.getMouseMode() : "");
        gamepadModeCombo.setSelectedItem(config.getGamepadMode() != null ? config.getGamepadMode() : "");
        otgCheck.setSelected(config.isOtgMode());
        
        // Device
        showTouchesCheck.setSelected(config.isShowTouches());
        stayAwakeCheck.setSelected(config.isStayAwake());
        turnScreenOffCheck.setSelected(config.isTurnScreenOff());
        
        // Window
        windowTitleField.setText(config.getWindowTitle() != null ? config.getWindowTitle() : "");
        fullscreenCheck.setSelected(config.isFullscreen());
        alwaysOnTopCheck.setSelected(config.isAlwaysOnTop());
        windowBorderlessCheck.setSelected(config.isWindowBorderless());
        
        // Recording
        recordFileField.setText(config.getRecordFile() != null ? config.getRecordFile() : "");
        recordFormatCombo.setSelectedItem(config.getRecordFormat() != null ? config.getRecordFormat() : "");
        recordOrientationCombo.setSelectedItem(config.getRecordOrientation());
        
        // Camera
        cameraFacingCombo.setSelectedItem(config.getCameraFacing() != null ? config.getCameraFacing() : "");
        cameraSizeField.setText(config.getCameraSize() != null ? config.getCameraSize() : "");
        cameraFpsField.setText(config.getCameraFps() != null ? config.getCameraFps() : "");
        
        // Advanced
        verbosityCombo.setSelectedItem(config.getVerbosity());
        printFpsCheck.setSelected(config.isPrintFps());
    }

    public void saveConfig(ScrcpyConfig config) {
        // Audio
        config.setAudioSource((String) audioSourceCombo.getSelectedItem());
        config.setAudioCodec((String) audioCodecCombo.getSelectedItem());
        config.setAudioBitRate(audioBitRateField.getText().trim());
        config.setNoAudio(noAudioCheck.isSelected());
        
        // Control
        config.setNoControl(noControlCheck.isSelected());
        config.setLegacyPaste(legacyPasteCheck.isSelected());
        config.setPreferText(preferTextCheck.isSelected());
        
        // Input
        String kbMode = (String) keyboardModeCombo.getSelectedItem();
        config.setKeyboardMode(kbMode.isEmpty() ? null : kbMode);
        String mouseMode = (String) mouseModeCombo.getSelectedItem();
        config.setMouseMode(mouseMode.isEmpty() ? null : mouseMode);
        String gamepadMode = (String) gamepadModeCombo.getSelectedItem();
        config.setGamepadMode(gamepadMode.isEmpty() ? null : gamepadMode);
        config.setOtgMode(otgCheck.isSelected());
        
        // Device
        config.setShowTouches(showTouchesCheck.isSelected());
        config.setStayAwake(stayAwakeCheck.isSelected());
        config.setTurnScreenOff(turnScreenOffCheck.isSelected());
        
        // Window
        config.setWindowTitle(windowTitleField.getText().trim().isEmpty() ? null : windowTitleField.getText().trim());
        config.setFullscreen(fullscreenCheck.isSelected());
        config.setAlwaysOnTop(alwaysOnTopCheck.isSelected());
        config.setWindowBorderless(windowBorderlessCheck.isSelected());
        
        // Recording
        config.setRecordFile(recordFileField.getText().trim().isEmpty() ? null : recordFileField.getText().trim());
        String recFormat = (String) recordFormatCombo.getSelectedItem();
        config.setRecordFormat(recFormat.isEmpty() ? null : recFormat);
        config.setRecordOrientation((String) recordOrientationCombo.getSelectedItem());
        
        // Camera
        String camFacing = (String) cameraFacingCombo.getSelectedItem();
        config.setCameraFacing(camFacing.isEmpty() ? null : camFacing);
        config.setCameraSize(cameraSizeField.getText().trim().isEmpty() ? null : cameraSizeField.getText().trim());
        config.setCameraFps(cameraFpsField.getText().trim().isEmpty() ? null : cameraFpsField.getText().trim());
        
        // Advanced
        config.setVerbosity((String) verbosityCombo.getSelectedItem());
        config.setPrintFps(printFpsCheck.isSelected());
    }
}
