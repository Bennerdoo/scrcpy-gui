@echo off
REM Script to copy scrcpy binaries to resources directory

echo Copying scrcpy binaries from C:\Program Files\scrcpy...

REM Check if scrcpy is installed
if not exist "C:\Program Files\scrcpy\scrcpy.exe" (
    echo ERROR: scrcpy not found at C:\Program Files\scrcpy
    echo Please install scrcpy first
    pause
    exit /b 1
)

REM Create resources directory if it doesn't exist
if not exist "resources\scrcpy" (
    mkdir resources\scrcpy
)

REM Copy all files from scrcpy directory
echo Copying files...
xcopy /E /I /Y "C:\Program Files\scrcpy\*" "resources\scrcpy\"

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Successfully copied scrcpy binaries to resources\scrcpy
    echo.
    dir /B resources\scrcpy
) else (
    echo.
    echo ERROR: Failed to copy files
    pause
    exit /b 1
)

echo.
echo Done!
pause
