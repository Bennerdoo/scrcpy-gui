@echo off
REM Build script for Scrcpy GUI (without Maven)

echo Building Scrcpy GUI...
echo.

REM Create output directories
if not exist "target\classes" mkdir target\classes
if not exist "target\lib" mkdir target\lib

echo Downloading libraries...
if not exist "target\lib\gson-2.10.1.jar" (
    curl -L -o target\lib\gson-2.10.1.jar https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
)
if not exist "target\lib\core-3.5.3.jar" (
    curl -L -o target\lib\core-3.5.3.jar https://repo1.maven.org/maven2/com/google/zxing/core/3.5.3/core-3.5.3.jar
)
if not exist "target\lib\javase-3.5.3.jar" (
    curl -L -o target\lib\javase-3.5.3.jar https://repo1.maven.org/maven2/com/google/zxing/javase/3.5.3/javase-3.5.3.jar
)

echo.
echo Compiling Java sources...
javac -d target\classes -cp "target\lib\gson-2.10.1.jar;target\lib\core-3.5.3.jar;target\lib\javase-3.5.3.jar" ^
    src\main\java\com\scrcpy\gui\*.java ^
    src\main\java\com\scrcpy\gui\config\*.java ^
    src\main\java\com\scrcpy\gui\core\*.java ^
    src\main\java\com\scrcpy\gui\panels\*.java ^
    src\main\java\com\scrcpy\gui\util\*.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo Compilation failed!
    pause
    exit /b 1
)

echo.
echo Creating JAR file...
cd target\classes
jar cvfe ..\scrcpy-gui.jar com.scrcpy.gui.ScrcpyGUI com\scrcpy\gui\*.class com\scrcpy\gui\config\*.class com\scrcpy\gui\core\*.class com\scrcpy\gui\panels\*.class
cd ..\..

echo.
echo Build successful!
echo.
echo To run the application:
echo   java -cp "target\scrcpy-gui.jar;target\lib\gson-2.10.1.jar" com.scrcpy.gui.ScrcpyGUI
echo.
echo Or use run.bat
pause
