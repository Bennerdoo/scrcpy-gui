@echo off
SETLOCAL ENABLEDELAYEDEXPANSION
REM Enhanced build script for Scrcpy GUI with resources

REM Set Java paths
set "JAVA_HOME=C:\Program Files\Java\jdk-21"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo =============================================
echo Building Scrcpy GUI with bundled resources
echo =============================================
echo.

REM Create output directories
if not exist "target\classes" mkdir target\classes
if not exist "target\lib" mkdir target\lib

echo Step 1: Downloading libraries...
echo ----------------------------------------
if not exist "target\lib\gson-2.10.1.jar" (
    curl -L -o target\lib\gson-2.10.1.jar https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
)
if not exist "target\lib\core-3.5.3.jar" (
    curl -L -o target\lib\core-3.5.3.jar https://repo1.maven.org/maven2/com/google/zxing/core/3.5.3/core-3.5.3.jar
)
if not exist "target\lib\javase-3.5.3.jar" (
    curl -L -o target\lib\javase-3.5.3.jar https://repo1.maven.org/maven2/com/google/zxing/javase/3.5.3/javase-3.5.3.jar
)
if not exist "target\lib\flatlaf-3.3.jar" (
    curl -L -o target\lib\flatlaf-3.3.jar https://repo1.maven.org/maven2/com/formdev/flatlaf/3.3/flatlaf-3.3.jar
)
echo Done!
echo.

echo Step 2: Compiling Java sources...
echo ----------------------------------------
javac -d target\classes -cp "target\lib\gson-2.10.1.jar;target\lib\core-3.5.3.jar;target\lib\javase-3.5.3.jar;target\lib\flatlaf-3.3.jar" ^
    src\main\java\com\scrcpy\gui\*.java ^
    src\main\java\com\scrcpy\gui\config\*.java ^
    src\main\java\com\scrcpy\gui\core\*.java ^
    src\main\java\com\scrcpy\gui\panels\*.java ^
    src\main\java\com\scrcpy\gui\util\*.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)
echo Compilation successful!
echo.

echo Step 3: Copying scrcpy resources...
echo ----------------------------------------
if exist "resources\scrcpy" (
    xcopy /E /I /Y resources\scrcpy target\classes\scrcpy\
    echo Resources copied!
) else (
    echo WARNING: No scrcpy resources found in resources\scrcpy
    echo Run copy-scrcpy.bat first to bundle scrcpy
)
echo.

echo Step 4: Extracting libraries into classes...
echo ----------------------------------------
cd target\classes
jar xf ..\lib\gson-2.10.1.jar
jar xf ..\lib\core-3.5.3.jar
jar xf ..\lib\javase-3.5.3.jar
jar xf ..\lib\flatlaf-3.3.jar
REM Remove META-INF to avoid conflicts
if exist "META-INF" rmdir /s /q META-INF
cd ..\..
echo Done!
echo.

echo Step 5: Creating fat JAR...
echo ----------------------------------------
cd target\classes

REM Create manifest
echo Main-Class: com.scrcpy.gui.ScrcpyGUI > manifest.txt

REM Create JAR with all classes and resources
jar cvfm ..\scrcpy-gui-fat.jar manifest.txt *

cd ..\..

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: JAR creation failed!
    pause
    exit /b 1
)

echo.
echo =============================================
echo Build successful!
echo =============================================
echo.
echo Output: target\scrcpy-gui-fat.jar
echo.

REM Show JAR size
for %%A in ("target\scrcpy-gui-fat.jar") do (
    set size=%%~zA
    set /a sizeInMB=!size! / 1048576
    echo JAR size: !sizeInMB! MB
)

echo.
echo To run the application:
echo   java -jar target\scrcpy-gui-fat.jar
echo.
echo To create Windows executable:
echo   package-exe.bat
echo.

pause
