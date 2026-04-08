@echo off
REM Helper script to run Scrcpy GUI with scrcpy in PATH

echo Refreshing PATH...
REM Refresh PATH for this session
set "PATH=%PATH%;C:\Program Files\scrcpy"

echo Starting Scrcpy GUI...
java -cp "target\classes;target\lib\gson-2.10.1.jar;target\lib\flatlaf-3.3.jar" com.scrcpy.gui.ScrcpyGUI
