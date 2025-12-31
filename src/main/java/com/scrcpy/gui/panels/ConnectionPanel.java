package com.scrcpy.gui.panels;

import com.scrcpy.gui.config.ScrcpyConfig;

import javax.swing.*;
import java.awt.*;

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
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        selectUsbCheck = new JCheckBox("Select USB Device (-d)");
        selectUsbCheck.setToolTipText("Use USB device if there is exactly one");
        add(selectUsbCheck, gbc);

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
}
