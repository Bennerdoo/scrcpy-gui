# Packaging ScrcpyGUI as a Windows Executable

This guide explains how to package the ScrcpyGUI application into a standalone Windows executable that includes all dependencies.

## Prerequisites

- Java JDK 14 or higher (JDK 21 recommended)
- scrcpy installed at `C:\Program Files\scrcpy`
- Windows 10/11

## Overview

The packaging process creates a standalone Windows installer that bundles:
- ScrcpyGUI Java application
- Java Runtime Environment (JRE)
- scrcpy binaries and all dependencies
- Gson library

The result is a ~150-200 MB installer that allows users to run ScrcpyGUI without installing Java or scrcpy separately.

## Quick Start

### Option 1: Run the Automated Script (Recommended)

```cmd
cd gui
package-exe.bat
```

This script will:
1. Build the fat JAR with bundled scrcpy
2. Create a Windows installer using jpackage
3. Output the installer to `dist\ScrcpyGUI-1.3.0.exe`

### Option 2: Manual Steps

#### Step 1: Copy scrcpy Binaries

```cmd
cd gui
copy-scrcpy.bat
```

This copies all scrcpy files from `C:\Program Files\scrcpy` to `gui\resources\scrcpy\`.

#### Step 2: Build the Fat JAR

```cmd
build-fat.bat
```

This creates `target\scrcpy-gui-fat.jar` (~7-8 MB) containing:
- All Java classes
- Gson library
- scrcpy binaries

#### Step 3: Test the JAR (Optional)

```cmd
java -jar target\scrcpy-gui-fat.jar
```

The GUI should launch and automatically use the bundled scrcpy executable.

#### Step 4: Create Windows Installer

```cmd
set "JAVA_HOME=C:\Program Files\Java\jdk-21"
set "PATH=%JAVA_HOME%\bin;%PATH%"

jpackage ^
    --type exe ^
    --name "ScrcpyGUI" ^
    --app-version "1.3.0" ^
    --vendor "Scrcpy Community" ^
    --description "Graphical User Interface for scrcpy with bundled binaries" ^
    --input target ^
    --main-jar scrcpy-gui-fat.jar ^
    --main-class com.scrcpy.gui.ScrcpyGUI ^
    --dest dist ^
    --win-dir-chooser ^
    --win-menu ^
    --win-shortcut ^
    --java-options "-Dfile.encoding=UTF-8"
```

## Output

The packaging process creates:

- `dist\ScrcpyGUI-1.3.0.exe` - Windows installer (~150-200 MB)

The installer will:
- Install the application to Program Files
- Create a desktop shortcut
- Add a Start Menu entry
- Include a bundled Java runtime

## Distribution

Simply distribute the `ScrcpyGUI-1.3.0.exe` file. Users can:
1. Double-click the installer
2. Follow the installation wizard
3. Run ScrcpyGUI from the desktop shortcut or Start Menu

No additional dependencies required!

## Technical Details

### How It Works

1. **Resource Extraction**: The application checks if scrcpy binaries are bundled in the JAR at `/scrcpy/`
2. **Temp Directory**: On first run, scrcpy binaries are extracted to a temporary directory
3. **Execution**: The GUI launches scrcpy from the temp directory
4. **Fallback**: If bundled scrcpy is not found, it falls back to using scrcpy from system PATH

### Files Included in Bundle

From scrcpy installation:
- `scrcpy.exe` - Main executable
- `scrcpy-server` - Android server component
- `SDL2.dll` - Graphics library
- `avcodec-61.dll`, `avformat-61.dll`, `avutil-59.dll` - FFmpeg libraries
- `swresample-5.dll` - Audio resampling
- `adb.exe`, `AdbWinApi.dll`, `AdbWinUsbApi.dll` - Android Debug Bridge
- `libusb-1.0.dll` - USB library

### Modified Files

The following files were modified to support bundled scrcpy:

1. **ScrcpyResourceManager.java** (NEW)
   - Handles extraction of bundled scrcpy resources
   - Provides fallback to system PATH

2. **CommandBuilder.java** (MODIFIED)
   - Uses `ScrcpyResourceManager.getScrcpyExecutable()` instead of hardcoded "scrcpy"

3. **pom.xml** (MODIFIED)
   - Includes `resources/` directory in build

4. **build-fat.bat** (NEW)
   - Compiles all classes
   - Bundles Gson and scrcpy resources
   - Creates fat JAR

5. **package-exe.bat** (NEW)
   - Builds fat JAR
   - Runs jpackage to create installer

## Troubleshooting

### "jpackage not found"

**Solution**: Ensure Java 14+ is installed and set JAVA_HOME:
```cmd
set "JAVA_HOME=C:\Program Files\Java\jdk-21"
set "PATH=%JAVA_HOME%\bin;%PATH%"
```

### "scrcpy not found at C:\Program Files\scrcpy"

**Solution**: Install scrcpy first or update the path in `copy-scrcpy.bat`

### Installer is very large (200+ MB)  

**Explanation**: This is expected. The installer includes:
- Java Runtime Environment (~100 MB)
- scrcpy binaries (~15 MB)
- Application code (~7 MB)
- Installer overhead (~50-80 MB)

### GUI doesn't find bundled scrcpy

**Solution**: 
1. Verify scrcpy files exist in `target/classes/scrcpy/` in the JAR
2. Test with: `java -jar target/scrcpy-gui-fat.jar`
3. Check console output for "Using bundled scrcpy from: ..."

## Alternative: JAR Distribution

If you prefer a smaller download (~7-8 MB), you can distribute the fat JAR directly:

1. Users need Java 11+ installed
2. Run: `java -jar scrcpy-gui-fat.jar`
3. All scrcpy binaries are still bundled

## Creating a Portable Version

To create a portable version that doesn't require installation:

```cmd
jpackage ^
    --type app-image ^
    --name "ScrcpyGUI" ^
    --input target ^
    --main-jar scrcpy-gui-fat.jar ^
    --main-class com.scrcpy.gui.ScrcpyGUI ^
    --dest dist-portable
```

This creates a folder (`dist-portable\ScrcpyGUI\`) that can be zipped and distributed.

## Customization

### Change Application Icon

1. Create a 256x256 PNG icon
2. Add to jpackage command:
   ```cmd
   --icon path\to\icon.png
   ```

### Change Install Directory

Add to jpackage:
```cmd
--install-dir "MyApps\ScrcpyGUI"
```

### Add File Associations

Add to jpackage:
```cmd
--file-associations file-association.properties
```

Where `file-association.properties` contains:
```properties
extension=mkv
description=Screen Recording
icon=recording-icon.png
```

## License

The bundled executable includes:
- ScrcpyGUI (your license)
- scrcpy (Apache 2.0)
- Java Runtime (GPL v2 with Classpath Exception)
- Gson (Apache 2.0)

Ensure compliance with all licenses when distributing.

---

**Made with Love by Bennerdoo** ❤️
