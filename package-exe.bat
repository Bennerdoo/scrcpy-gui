@echo off
SETLOCAL ENABLEDELAYEDEXPANSION
REM Script to package Scrcpy GUI as a Windows executable using jpackage

echo =============================================
echo Scrcpy GUI - Windows Executable Packager
echo =============================================
echo.

REM Set Java home to JDK 21
set "JAVA_HOME=C:\Program Files\Java\jdk-21"
set "PATH=%JAVA_HOME%\bin;%PATH%"

REM Verify jpackage is available
echo Checking jpackage...
jpackage --version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: jpackage not found
    echo Please ensure Java 14+ is installed
    pause
    exit /b 1
)
echo jpackage found!
echo.

REM Step 1: Build the fat JAR
echo Step 1: Building fat JAR...
echo ----------------------------------------
call build-fat.bat
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Build failed
    pause
    exit /b 1
)
echo.

REM Step 2: Check if JAR exists
if not exist "target\scrcpy-gui-fat.jar" (
    echo ERROR: JAR file not found
    echo Expected: target\scrcpy-gui-fat.jar
    pause
    exit /b 1
)

REM Step 3: Create output directory
echo Step 2: Preparing output directory...
echo ----------------------------------------
if exist "dist" (
    rmdir /s /q dist
)
mkdir dist
echo.

REM Step 4: Run jpackage
echo Step 3: Creating Windows executable installer...
echo ----------------------------------------
echo This may take several minutes...
echo.

jpackage ^
    --type exe ^
    --icon ..\app\data\icon.ico ^
    --name "ScrcpyGUI" ^
    --app-version "1.3.0" ^
    --vendor "Bennerdoo" ^
    --description "Graphical User Interface for scrcpy with bundled binaries - Made with Love by Bennerdoo" ^
    --input target ^
    --main-jar scrcpy-gui-fat.jar ^
    --main-class com.scrcpy.gui.ScrcpyGUI ^
    --dest dist ^
    --win-dir-chooser ^
    --win-menu ^
    --win-shortcut ^
    --java-options "-Dfile.encoding=UTF-8"

if %ERRORLEVEL% NEQ 0 (
    echo ERROR: jpackage failed
    pause
    exit /b 1
)

echo.
echo =============================================
echo SUCCESS! Installer created
echo =============================================
echo.
echo Installer location: dist\ScrcpyGUI-1.3.0.exe
echo.

REM Show file size
for %%A in ("dist\ScrcpyGUI-1.3.0.exe") do (
    set size=%%~zA
    set /a sizeInMB=!size! / 1048576
    echo Installer size: !sizeInMB! MB
)

echo.
echo You can now distribute dist\ScrcpyGUI-1.3.0.exe
echo Users can install and run it without Java or scrcpy installed!
echo.

pause
