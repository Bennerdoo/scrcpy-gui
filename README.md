# Scrcpy GUI

A comprehensive Java Swing-based graphical user interface for [scrcpy](https://github.com/Genymobile/scrcpy).

## Overview

This GUI provides an intuitive interface for all scrcpy command-line options, making it easy to configure and launch scrcpy without memorizing complex commands.

## Features

- **Organized Options**: All scrcpy options organized into logical categories:
  - Connection (device selection, TCP/IP, tunnels)
  - Video (codec, quality, FPS, orientation)
  - Audio (source, codec, bit rate)
  - Control (keyboard, mouse, gamepad)
  - Device (display, power, touches)
  - Window (size, position, fullscreen)
  - Recording (file format, orientation)
  - Camera (facing, size, FPS)
  - Advanced (verbosity, render driver)

- **Configuration Management**: Save and load configuration profiles
- **Real-time Command Display**: See the exact scrcpy command that will be executed
- **Process Output**: View scrcpy output in real-time
- **Auto-save**: Last used configuration is automatically saved

## Prerequisites

1. **Java 11 or higher** installed
2. **scrcpy** installed and available in your system PATH
3. **Maven** (for building from source)

### Installing scrcpy

Follow the installation instructions for your platform:
- **Windows**: https://github.com/Genymobile/scrcpy/blob/master/doc/windows.md
- **Linux**: https://github.com/Genymobile/scrcpy/blob/master/doc/linux.md
- **macOS**: https://github.com/Genymobile/scrcpy/blob/master/doc/macos.md

Verify scrcpy is installed:
```bash
scrcpy --version
```

## Building

```bash
cd gui
mvn clean package
```

This will create:
- `target/scrcpy-gui-1.3.0.jar` - Simple JAR
- `target/scrcpy-gui-1.3.0-jar-with-dependencies.jar` - Fat JAR with all dependencies

### Automated Builds with Jenkins

For automated continuous integration and deployment, see [JENKINS_SETUP.md](JENKINS_SETUP.md) for complete Jenkins configuration.

Quick setup:
1. Create Jenkins Pipeline job
2. Point to `Jenkinsfile` in this directory
3. Build automatically packages:
   - JAR with dependencies
   - Windows .exe installer (71 MB, includes Java runtime and scrcpy)

## Running

### From JAR (Recommended)

```bash
java -jar target/scrcpy-gui-1.3.0-jar-with-dependencies.jar
```

### From Maven

```bash
mvn exec:java -Dexec.mainClass="com.scrcpy.gui.ScrcpyGUI"
```

### From IDE

Run the `com.scrcpy.gui.ScrcpyGUI` class

## Usage

1. **Configure Options**: Select your desired options from the tabs:
   - **Connection**: Choose device (USB/TCP/IP) and connection settings
   - **Video**: Configure video quality, codec, and size
   - **All Options**: Access all other scrcpy features

2. **View Command**: The generated scrcpy command is displayed in the "Generated Command" area

3. **Start scrcpy**: Click the "Start scrcpy" button to launch scrcpy with your configuration

4. **Monitor Output**: View scrcpy's output in real-time in the output panel

5. **Save Configuration**: Save your current settings as a named profile for later use

6. **Load Configuration**: Load previously saved configurations

## Configuration Files

Configurations are saved as JSON files in:
- **Windows**: `C:\Users\<username>\.scrcpy-gui\`
- **Linux/macOS**: `~/.scrcpy-gui/`

The default configuration (`default.json`) is automatically saved when you start scrcpy.

## Common Use Cases

### Basic Mirroring
1. Leave all options at default
2. Click "Start scrcpy"

### High Quality Recording
1. Set Video Codec to "h265"
2. Set Max Size to "1920"
3. Set Max FPS to "60"
4. Go to Recording tab, set Record File to "output.mp4"
5. Click "Start scrcpy"

### Wireless Connection
1. Go to Connection tab
2. Enter your device IP in "TCP/IP Address" (e.g., `192.168.1.100:5555`)
3. Click "Start scrcpy"

### Camera Mirroring
1. Set Video Source to "camera"
2. Go to Camera tab
3. Select Camera Facing (front/back)
4. Set Camera Size if needed
5. Click "Start scrcpy"

## Troubleshooting

### "Failed to start scrcpy"
- Make sure scrcpy is installed and in your PATH
- Run `scrcpy --version` in terminal to verify
- Check the output panel for specific error messages

### "No devices/emulators found"
- Enable USB debugging on your Android device
- Connect device via USB or set up TCP/IP connection
- Run `adb devices` to verify device is recognized

### Application won't start
- Verify Java 11 or higher is installed: `java -version`
- Make sure you're using the jar-with-dependencies version

## Keyboard Shortcuts

- The GUI itself doesn't have keyboard shortcuts
- Once scrcpy is running, use scrcpy's built-in shortcuts (see scrcpy documentation)

## Project Structure

```
gui/
├── pom.xml
├── README.md
└── src/main/java/com/scrcpy/gui/
    ├── ScrcpyGUI.java              # Main application
    ├── config/
    │   ├── ScrcpyConfig.java       # Configuration data model
    │   └── ConfigurationManager.java  # Save/load profiles
    ├── core/
    │   ├── CommandBuilder.java     # Build scrcpy command
    │   └── ProcessExecutor.java    # Execute and monitor scrcpy
    └── panels/
        ├── ConnectionPanel.java    # Connection options
        ├── VideoPanel.java         # Video options
        └── AllOptionsPanel.java    # All other options
```

## Contributing

This is a community-created GUI for scrcpy. For issues with scrcpy itself, please visit the [official scrcpy repository](https://github.com/Genymobile/scrcpy).

## License

This GUI application is provided as-is. scrcpy itself is licensed under Apache License 2.0.

## Acknowledgments

- [scrcpy](https://github.com/Genymobile/scrcpy) by Romain Vimont and contributors
- All scrcpy command-line options are documented in the [official documentation](https://github.com/Genymobile/scrcpy/tree/master/doc)

---

**Made with Love by Bennerdoo** ❤️
