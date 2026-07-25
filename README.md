# Scrcpy GUI

A comprehensive graphical user interface for [scrcpy](https://github.com/Genymobile/scrcpy) — mirror, record, and control your Android device with a single click.

> **No setup required.** The installer bundles everything — scrcpy, Java runtime, and all dependencies. Just download and run.

## Overview

Scrcpy GUI provides an intuitive, tabbed interface for every scrcpy option, making it easy to configure and launch scrcpy without memorizing complex commands. Download the app, open it, and you're ready to go.

**[⬇️ Download the latest release](https://github.com/Bennerdoo/scrcpy-gui/releases/tag/Layout_update)**

## Advantages

Using the Scrcpy GUI comes with significant benefits over the raw command-line interface:
- **Zero Setup**: Everything is bundled in the installer — scrcpy, Java runtime, and all dependencies. No configuration required.
- **No Command Memorization**: Stop typing long strings of flags. Every scrcpy option is available visually.
- **Error Reduction**: The UI automatically formats commands correctly, eliminating typos and syntax errors.
- **Workflow Efficiency**: Save and load different configuration profiles for distinct devices or use cases.
- **Real-Time Feedback**: View exactly what command is being executed and read the output logs directly within the app.
- **Accessibility**: Lowers the barrier to entry for users who want to leverage scrcpy's powerful features but aren't comfortable with the terminal.

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

## Installation

### Windows (Recommended)

1. **[Download the `.exe` installer](https://github.com/Bennerdoo/scrcpy-gui/releases/tag/Layout_update)**
2. Run the installer
3. Launch **Scrcpy GUI** from the Start menu or desktop shortcut

That's it. The installer includes scrcpy, the Java runtime, and all required dependencies — no additional setup needed.

### Other Platforms (JAR)

A cross-platform JAR is also available on the [releases page](https://github.com/Bennerdoo/scrcpy-gui/releases/tag/Layout_update) for Linux and macOS.

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

### "No devices/emulators found"
- Enable **USB Debugging** on your Android device (Settings → Developer Options)
- Connect your device via USB or set up a TCP/IP wireless connection
- Run `adb devices` in a terminal to verify your device is recognized

### Application won't start
- Try re-downloading the installer from the [releases page](https://github.com/Bennerdoo/scrcpy-gui/releases/tag/Layout_update)
- Make sure you're using the official `.exe` installer or the full JAR from the releases page

### Laggy or low quality video
- Lower Max Size to `1280` and reduce the bitrate
- Switch codec from `h265` to `h264` for lower CPU usage
- Check the **output panel** inside the app for detailed error messages

## Building from Source

> This section is for developers who want to contribute or build the app themselves.

**Requirements:** Java 11+, Maven

```bash
cd gui
mvn clean package
```

This creates:
- `target/scrcpy-gui-1.3.0.jar` — Simple JAR
- `target/scrcpy-gui-1.3.0-jar-with-dependencies.jar` — Fat JAR with all dependencies

### Automated Builds with Jenkins

For CI/CD setup, see [JENKINS_SETUP.md](JENKINS_SETUP.md). The pipeline automatically packages:
- JAR with dependencies
- Windows `.exe` installer (includes Java runtime and scrcpy)

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

This is a community-created GUI for scrcpy. Contributions of all kinds are welcome — code, documentation, testing, and feature suggestions.

**[Open an issue or submit a pull request →](https://github.com/Bennerdoo/scrcpy-gui.git)**

For issues with scrcpy itself, please visit the [official scrcpy repository](https://github.com/Genymobile/scrcpy).

## License

This GUI application is provided as-is. scrcpy itself is licensed under Apache License 2.0.

## Acknowledgments

- [scrcpy](https://github.com/Genymobile/scrcpy) by Romain Vimont and contributors
- All scrcpy command-line options are documented in the [official documentation](https://github.com/Genymobile/scrcpy/tree/master/doc)

---

**Made with Love by Bennerdoo** ❤️
