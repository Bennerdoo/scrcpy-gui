package com.scrcpy.gui.panels;

import com.scrcpy.gui.config.ScrcpyConfig;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.scrcpy.gui.core.AdbRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Panel for connection and device selection options
 */
public class ConnectionPanel extends JPanel {
    private JTextField serialField;
    private JCheckBox selectUsbCheck;
    private JCheckBox selectTcpipCheck;
    private JTextField tcpipAddressField;
    private JTextField portRangeField;
    private JCheckBox forceAdbForwardCheck;
    private JTextField tunnelHostField;
    private JTextField tunnelPortField;

    // Wireless Pairing
    private JTextField pairingIpField;
    private JTextField pairingCodeField;
    private JButton pairButton;
    private JButton showQrButton;
    private AdbRunner adbRunner;

    public ConnectionPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Serial number
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("Serial Number (-s):"), gbc);
        gbc.gridx = 1;
        serialField = new JTextField(20);
        serialField.setToolTipText("Device serial number (for multiple devices)");
        add(serialField, gbc);

        row++;

        // Select USB
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        selectUsbCheck = new JCheckBox("Select USB Device (-d)");
        selectUsbCheck.setToolTipText("Use USB device if there is exactly one");
        add(selectUsbCheck, gbc);

        gbc.gridx = 1;
        JButton usbHelpButton = new JButton("How to connect (USB)");
        usbHelpButton.setToolTipText("Show instructions for USB debugging");
        usbHelpButton.addActionListener(e -> showUsbHelpDialog());
        add(usbHelpButton, gbc);

        row++;

        // Select TCP/IP
        gbc.gridx = 0; gbc.gridy = row;
        selectTcpipCheck = new JCheckBox("Select TCP/IP Device (-e)");
        selectTcpipCheck.setToolTipText("Use TCP/IP device if there is exactly one");
        add(selectTcpipCheck, gbc);

        row++;

        // TCP/IP address
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        add(new JLabel("TCP/IP Address (--tcpip):"), gbc);
        gbc.gridx = 1;
        tcpipAddressField = new JTextField(20);
        tcpipAddressField.setToolTipText("IP address and optional port (e.g., 192.168.1.100:5555)");
        add(tcpipAddressField, gbc);

        row++;

        // Port range
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("Port Range (-p):"), gbc);
        gbc.gridx = 1;
        portRangeField = new JTextField(20);
        portRangeField.setToolTipText("TCP port range (default: 27183:27199)");
        add(portRangeField, gbc);

        row++;

        // Force ADB forward
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        forceAdbForwardCheck = new JCheckBox("Force ADB Forward (--force-adb-forward)");
        forceAdbForwardCheck.setToolTipText("Do not attempt to use 'adb reverse'");
        add(forceAdbForwardCheck, gbc);

        row++;

        // Tunnel host
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        add(new JLabel("Tunnel Host (--tunnel-host):"), gbc);
        gbc.gridx = 1;
        tunnelHostField = new JTextField(20);
        tunnelHostField.setToolTipText("IP address of the adb tunnel (default: localhost)");
        add(tunnelHostField, gbc);

        row++;

        // Tunnel port
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("Tunnel Port (--tunnel-port):"), gbc);
        gbc.gridx = 1;
        tunnelPortField = new JTextField(20);
        tunnelPortField.setToolTipText("TCP port of the adb tunnel");
        add(tunnelPortField, gbc);

        row++;

        // Wireless Pairing Section
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        
        JPanel wirelessPanel = new JPanel(new GridBagLayout());
        wirelessPanel.setBorder(BorderFactory.createTitledBorder("Wireless Pairing (Android 11+)"));
        GridBagConstraints wGbc = new GridBagConstraints();
        wGbc.insets = new Insets(5, 5, 5, 5);
        wGbc.anchor = GridBagConstraints.WEST;
        wGbc.fill = GridBagConstraints.HORIZONTAL;

        wGbc.gridx = 0; wGbc.gridy = 0;
        wirelessPanel.add(new JLabel("Pairing IP:Port:"), wGbc);
        wGbc.gridx = 1;
        pairingIpField = new JTextField(15);
        pairingIpField.setToolTipText("IP address and port shown on device (e.g., 192.168.1.100:40001)");
        wirelessPanel.add(pairingIpField, wGbc);

        wGbc.gridx = 0; wGbc.gridy = 1;
        wirelessPanel.add(new JLabel("Pairing Code:"), wGbc);
        wGbc.gridx = 1;
        pairingCodeField = new JTextField(10);
        pairingCodeField.setToolTipText("6-digit pairing code");
        wirelessPanel.add(pairingCodeField, wGbc);

        wGbc.gridx = 0; wGbc.gridy = 2; wGbc.gridwidth = 2;
        wGbc.fill = GridBagConstraints.NONE;
        wGbc.anchor = GridBagConstraints.CENTER;
        
        JPanel wirelessButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pairButton = new JButton("Pair with PIN");
        pairButton.addActionListener(e -> performPairing());
        wirelessButtonsPanel.add(pairButton);

        showQrButton = new JButton("Show Pairing QR Code");
        showQrButton.addActionListener(e -> showQrCodeDialog());
        wirelessButtonsPanel.add(showQrButton);

        wirelessPanel.add(wirelessButtonsPanel, wGbc);
        add(wirelessPanel, gbc);

        adbRunner = new AdbRunner();

        // Restore defaults for layout
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.0;

        // Add filler
        gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        add(Box.createVerticalGlue(), gbc);

        setBorder(BorderFactory.createTitledBorder("Connection Options"));
    }

    public void loadConfig(ScrcpyConfig config) {
        serialField.setText(config.getSerial() != null ? config.getSerial() : "");
        selectUsbCheck.setSelected(config.isSelectUsb());
        selectTcpipCheck.setSelected(config.isSelectTcpip());
        tcpipAddressField.setText(config.getTcpipAddress() != null ? config.getTcpipAddress() : "");
        portRangeField.setText(config.getPortRange() != null ? config.getPortRange() : "");
        forceAdbForwardCheck.setSelected(config.isForceAdbForward());
        tunnelHostField.setText(config.getTunnelHost() != null ? config.getTunnelHost() : "");
        tunnelPortField.setText(config.getTunnelPort() != null ? config.getTunnelPort() : "");
    }

    public void saveConfig(ScrcpyConfig config) {
        config.setSerial(serialField.getText().trim().isEmpty() ? null : serialField.getText().trim());
        config.setSelectUsb(selectUsbCheck.isSelected());
        config.setSelectTcpip(selectTcpipCheck.isSelected());
        config.setTcpipAddress(tcpipAddressField.getText().trim().isEmpty() ? null : tcpipAddressField.getText().trim());
        config.setPortRange(portRangeField.getText().trim().isEmpty() ? null : portRangeField.getText().trim());
        config.setForceAdbForward(forceAdbForwardCheck.isSelected());
        config.setTunnelHost(tunnelHostField.getText().trim().isEmpty() ? null : tunnelHostField.getText().trim());
        config.setTunnelPort(tunnelPortField.getText().trim().isEmpty() ? null : tunnelPortField.getText().trim());
    }
    private void showUsbHelpDialog() {
        String message = "<html>" +
                "<b>How to connect via USB:</b><br><br>" +
                "1. On your phone, go to <b>Settings</b> &gt; <b>About Phone</b>.<br>" +
                "2. Tap <b>Build Number</b> 7 times to enable Developer Options.<br>" +
                "3. Go back to main settings and find <b>Developer Options</b>.<br>" +
                "4. Turn on <b>USB Debugging</b>.<br>" +
                "5. Connect your phone to your PC via USB cable.<br>" +
                "6. Allow the debugging prompt on your phone screen." +
                "</html>";
        JOptionPane.showMessageDialog(this, message, "USB Connection Help", JOptionPane.INFORMATION_MESSAGE);
    }

    private void performPairing() {
        String ipPort = pairingIpField.getText().trim();
        String code = pairingCodeField.getText().trim();

        if (ipPort.isEmpty() || code.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both IP:Port and Pairing Code.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        pairButton.setEnabled(false);
        adbRunner.pair(ipPort, code, result -> {
            SwingUtilities.invokeLater(() -> {
                pairButton.setEnabled(true);
                if (result.isSuccess()) {
                    JOptionPane.showMessageDialog(this, "Pairing successful! Now use connection port on device to connect.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Pairing failed:\n" + result.stderr + "\n" + result.stdout, "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
    }

    private void showQrCodeDialog() {
        Random random = new Random();
        String name = "scrcpy-" + (1000 + random.nextInt(9000));
        String code = String.format("%06d", random.nextInt(1000000));
        String qrContent = "WIFI:T:ADB;S:" + name + ";P:" + code + ";;";

        try {
            BufferedImage qrImage = generateQRCodeImage(qrContent);
            
            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.add(new JLabel(new ImageIcon(qrImage)), BorderLayout.CENTER);
            panel.add(new JLabel("<html><center>Scan this with 'Pair device with QR code' in Wireless Debugging settings.<br>Wait matching confirmation.</center></html>"), BorderLayout.SOUTH);

            JOptionPane.showMessageDialog(this, panel, "Wireless Pairing QR Code", JOptionPane.PLAIN_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to generate QR code: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private BufferedImage generateQRCodeImage(String text) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
