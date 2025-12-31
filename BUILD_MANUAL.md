# Building Without Maven

If you don't have Maven installed, you can build the project manually:

## Windows

1. Run the build script:
   ```cmd
   build.bat
   ```

2. Run the application:
   ```cmd
   run.bat
   ```

## Manual Build Steps

### 1. Create directories
```cmd
mkdir target\classes
mkdir target\lib
```

### 2. Download Gson library
Download gson-2.10.1.jar from:
https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar

Place it in `target\lib\` directory.

### 3. Compile
```cmd
javac -d target\classes -cp "target\lib\gson-2.10.1.jar" ^
    src\main\java\com\scrcpy\gui\*.java ^
    src\main\java\com\scrcpy\gui\config\*.java ^
    src\main\java\com\scrcpy\gui\core\*.java ^
    src\main\java\com\scrcpy\gui\panels\*.java
```

### 4. Create JAR
```cmd
cd target\classes
jar cvfe ..\scrcpy-gui.jar com.scrcpy.gui.ScrcpyGUI com\scrcpy\gui\*.class com\scrcpy\gui\config\*.class com\scrcpy\gui\core\*.class com\scrcpy\gui\panels\*.class
cd ..\..
```

### 5. Run
```cmd
java -cp "target\scrcpy-gui.jar;target\lib\gson-2.10.1.jar" com.scrcpy.gui.ScrcpyGUI
```

## Linux/macOS

Replace `;` with `:` in classpath and use forward slashes `/` for paths.

```bash
# Download Gson
mkdir -p target/lib
curl -L -o target/lib/gson-2.10.1.jar https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar

# Compile
mkdir -p target/classes
javac -d target/classes -cp "target/lib/gson-2.10.1.jar" \
    src/main/java/com/scrcpy/gui/*.java \
    src/main/java/com/scrcpy/gui/config/*.java \
    src/main/java/com/scrcpy/gui/core/*.java \
    src/main/java/com/scrcpy/gui/panels/*.java

# Create JAR
cd target/classes
jar cvfe ../scrcpy-gui.jar com.scrcpy.gui.ScrcpyGUI com/scrcpy/gui/*.class com/scrcpy/gui/config/*.class com/scrcpy/gui/core/*.class com/scrcpy/gui/panels/*.class
cd ../..

# Run
java -cp "target/scrcpy-gui.jar:target/lib/gson-2.10.1.jar" com.scrcpy.gui.ScrcpyGUI
```
