package com.scrcpy.gui.panels;

import com.scrcpy.gui.config.ScrcpyConfig;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for video configuration options
 */
public class VideoPanel extends JPanel {
    private JComboBox<String> videoSourceCombo;
    private JComboBox<String> videoCodecCombo;
    private JTextField videoEncoderField;
    private JTextField videoBitRateField;
    private JTextField maxSizeField;
    private JTextField maxFpsField;
    private JTextField videoBufferField;
    private JTextField videoCodecOptionsField;
    private JCheckBox noVideoCheck;
    private JCheckBox noVideoPlaybackCheck;
    private JCheckBox noMipmapsCheck;
    private JCheckBox noDownsizeOnErrorCheck;

    public VideoPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Video source
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("Video Source:"), gbc);
        gbc.gridx = 1;
        videoSourceCombo = new JComboBox<>(new String[]{"display", "camera"});
        add(videoSourceCombo, gbc);

        row++;

        // Video codec
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("Video Codec:"), gbc);
        gbc.gridx = 1;
        videoCodecCombo = new JComboBox<>(new String[]{"h264", "h265", "av1"});
        add(videoCodecCombo, gbc);

        row++;

        // Video encoder
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("Video Encoder:"), gbc);
        gbc.gridx = 1;
        videoEncoderField = new JTextField(20);
        videoEncoderField.setToolTipText("Specific MediaCodec encoder (see --list-encoders)");
        add(videoEncoderField, gbc);

        row++;

        // Video bit rate
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("Video Bit Rate (-b):"), gbc);
        gbc.gridx = 1;
        videoBitRateField = new JTextField("8M", 20);
        videoBitRateField.setToolTipText("Video bit rate (e.g., 8M, 10000000)");
        add(videoBitRateField, gbc);

        row++;

        // Max size
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("Max Size (-m):"), gbc);
        gbc.gridx = 1;
        maxSizeField = new JTextField(20);
        maxSizeField.setToolTipText("Max video dimension (e.g., 1024, 1920)");
        add(maxSizeField, gbc);

        row++;

        // Max FPS
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("Max FPS:"), gbc);
        gbc.gridx = 1;
        maxFpsField = new JTextField(20);
        maxFpsField.setToolTipText("Maximum frame rate (e.g., 60)");
        add(maxFpsField, gbc);

        row++;

        // Video buffer
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("Video Buffer (ms):"), gbc);
        gbc.gridx = 1;
        videoBufferField = new JTextField(20);
        videoBufferField.setToolTipText("Buffering delay in milliseconds");
        add(videoBufferField, gbc);

        row++;

        // Video codec options
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("Codec Options:"), gbc);
        gbc.gridx = 1;
        videoCodecOptionsField = new JTextField(20);
        videoCodecOptionsField.setToolTipText("Comma-separated codec options");
        add(videoCodecOptionsField, gbc);

        row++;

        // Checkboxes
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        noVideoCheck = new JCheckBox("Disable Video (--no-video)");
        add(noVideoCheck, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row;
        noVideoPlaybackCheck = new JCheckBox("Disable Video Playback (--no-video-playback)");
        add(noVideoPlaybackCheck, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row;
        noMipmapsCheck = new JCheckBox("Disable Mipmaps (--no-mipmaps)");
        add(noMipmapsCheck, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row;
        noDownsizeOnErrorCheck = new JCheckBox("Disable Downsize on Error (--no-downsize-on-error)");
        add(noDownsizeOnErrorCheck, gbc);

        // Add filler
        gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        add(Box.createVerticalGlue(), gbc);

        setBorder(BorderFactory.createTitledBorder("Video Options"));
    }

    public void loadConfig(ScrcpyConfig config) {
        videoSourceCombo.setSelectedItem(config.getVideoSource());
        videoCodecCombo.setSelectedItem(config.getVideoCodec());
        videoEncoderField.setText(config.getVideoEncoder() != null ? config.getVideoEncoder() : "");
        videoBitRateField.setText(config.getVideoBitRate());
        maxSizeField.setText(config.getMaxSize() != null ? config.getMaxSize() : "");
        maxFpsField.setText(config.getMaxFps() != null ? config.getMaxFps() : "");
        videoBufferField.setText(config.getVideoBuffer() != null ? config.getVideoBuffer() : "");
        videoCodecOptionsField.setText(config.getVideoCodecOptions() != null ? config.getVideoCodecOptions() : "");
        noVideoCheck.setSelected(config.isNoVideo());
        noVideoPlaybackCheck.setSelected(config.isNoVideoPlayback());
        noMipmapsCheck.setSelected(config.isNoMipmaps());
        noDownsizeOnErrorCheck.setSelected(config.isNoDownsizeOnError());
    }

    public void saveConfig(ScrcpyConfig config) {
        config.setVideoSource((String) videoSourceCombo.getSelectedItem());
        config.setVideoCodec((String) videoCodecCombo.getSelectedItem());
        config.setVideoEncoder(videoEncoderField.getText().trim().isEmpty() ? null : videoEncoderField.getText().trim());
        config.setVideoBitRate(videoBitRateField.getText().trim());
        config.setMaxSize(maxSizeField.getText().trim().isEmpty() ? null : maxSizeField.getText().trim());
        config.setMaxFps(maxFpsField.getText().trim().isEmpty() ? null : maxFpsField.getText().trim());
        config.setVideoBuffer(videoBufferField.getText().trim().isEmpty() ? null : videoBufferField.getText().trim());
        config.setVideoCodecOptions(videoCodecOptionsField.getText().trim().isEmpty() ? null : videoCodecOptionsField.getText().trim());
        config.setNoVideo(noVideoCheck.isSelected());
        config.setNoVideoPlayback(noVideoPlaybackCheck.isSelected());
        config.setNoMipmaps(noMipmapsCheck.isSelected());
        config.setNoDownsizeOnError(noDownsizeOnErrorCheck.isSelected());
    }
}
