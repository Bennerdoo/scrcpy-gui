# Installing scrcpy on Windows

## Method 1: Download Pre-built Release (Recommended)

### Step 1: Download scrcpy

1. Visit the official scrcpy releases page:
   https://github.com/Genymobile/scrcpy/releases

2. Download the latest Windows release (usually named `scrcpy-win64-vX.X.X.zip`)

3. Extract the ZIP file to a permanent location, for example:
   ```
   C:\Program Files\scrcpy
   ```

### Step 2: Add scrcpy to PATH

#### Method A: Using System Properties (GUI)

1. **Open System Properties:**
   - Press `Windows + R` to open Run dialog
   - Type `sysdm.cpl` and press Enter
   - OR: Right-click "This PC" → Properties → Advanced system settings

2. **Access Environment Variables:**
   - Click the "Advanced" tab
   - Click "Environment Variables" button at the bottom

3. **Edit PATH:**
   - Under "User variables" or "System variables", find and select "Path"
   - Click "Edit"
   - Click "New"
   - Add the path to your scrcpy folder (e.g., `C:\Program Files\scrcpy`)
   - Click "OK" on all dialogs

4. **Verify Installation:**
   - Open a **NEW** Command Prompt or PowerShell window
   - Type: `scrcpy --version`
   - You should see the scrcpy version number

#### Method B: Using PowerShell (Quick)

Run this in PowerShell as Administrator:

```powershell
# Set the scrcpy installation path
$scrcpyPath = "C:\Program Files\scrcpy"

# Add to User PATH
$currentPath = [Environment]::GetEnvironmentVariable("Path", "User")
$newPath = "$currentPath;$scrcpyPath"
[Environment]::SetEnvironmentVariable("Path", $newPath, "User")

Write-Host "scrcpy added to PATH. Please restart your terminal."
```

#### Method C: Using Command Prompt

Run this in Command Prompt as Administrator:

```cmd
setx PATH "%PATH%;C:\Program Files\scrcpy"
```

**Note:** Replace `C:\Program Files\scrcpy` with your actual scrcpy installation path.

### Step 3: Verify Installation

1. **Close and reopen** your terminal (Command Prompt or PowerShell)

2. Run:
   ```cmd
   scrcpy --version
   ```

3. You should see output like:
   ```
   scrcpy 3.3.4 <commit>
   ```

## Method 2: Using Package Managers

### Using Scoop

```powershell
scoop install scrcpy
```

### Using Chocolatey

```powershell
choco install scrcpy
```

These package managers automatically add scrcpy to your PATH.

## Troubleshooting

### "scrcpy is not recognized as an internal or external command"

**Solutions:**

1. **Verify PATH was updated:**
   ```cmd
   echo %PATH%
   ```
   Look for your scrcpy directory in the output.

2. **Restart your terminal** - PATH changes require a new terminal session.

3. **Use full path temporarily:**
   ```cmd
   "C:\Program Files\scrcpy\scrcpy.exe" --version
   ```

4. **Check scrcpy.exe exists:**
   - Navigate to your scrcpy folder
   - Confirm `scrcpy.exe` is present

### PATH not updating

1. **System vs User PATH:**
   - User PATH: Only affects your user account
   - System PATH: Affects all users (requires admin rights)
   - Try adding to System PATH if User PATH doesn't work

2. **Manual verification:**
   - Open a NEW terminal
   - Run: `where scrcpy`
   - This should show the path to scrcpy.exe

## Quick Test After Installation

Once scrcpy is in your PATH, test it:

```cmd
# Check version
scrcpy --version

# List connected devices
adb devices

# Run scrcpy (with device connected)
scrcpy
```

## Prerequisites

Before using scrcpy, ensure:

1. **ADB drivers installed** (usually comes with Android SDK Platform Tools)
2. **USB debugging enabled** on your Android device
3. **Device connected** via USB or wireless ADB

### Installing ADB if needed

Download Android SDK Platform Tools:
https://developer.android.com/studio/releases/platform-tools

Extract and add to PATH using the same method as scrcpy.

## Current Installation Location

Since you already have the scrcpy source code, you can also build it from source. However, using the pre-built release is much simpler and recommended.

Your source code location: `C:\Users\Bennerdo\OneDrive\Documents\PROJECTS\scrcpy-master`

## Next Steps

After installing scrcpy and adding to PATH:

1. Run the Scrcpy GUI:
   ```cmd
   cd C:\Users\Bennerdo\OneDrive\Documents\PROJECTS\scrcpy-master\gui
   .\run.bat
   ```

2. The GUI will use scrcpy from your PATH automatically.

3. Test with a simple command:
   - Connect your Android device
   - Click "Start scrcpy" in the GUI
