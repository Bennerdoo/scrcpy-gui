# Contributing to Scrcpy GUI

Thank you for considering contributing to Scrcpy GUI! This document provides guidelines for contributing to the project.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Git
- scrcpy installed on your system

### Setting Up Development Environment

1. **Fork and Clone**
   ```bash
   git clone https://github.com/YOUR_USERNAME/scrcpy-gui.git
   cd scrcpy-gui/gui
   ```

2. **Build the Project**
   ```bash
   mvn clean package
   ```

3. **Run from Source**
   ```bash
   mvn exec:java -Dexec.mainClass="com.scrcpy.gui.ScrcpyGUI"
   ```
   
   Or use the provided batch file:
   ```bash
   run.bat
   ```

## How to Contribute

### Reporting Bugs

Before submitting a bug report:
- Check if the issue is with scrcpy itself (test with command-line scrcpy)
- Search existing issues to avoid duplicates

When submitting a bug report, include:
- Version of Scrcpy GUI
- Version of scrcpy
- Operating System
- Steps to reproduce
- Expected vs actual behavior
- Screenshots if applicable

### Suggesting Enhancements

Enhancement suggestions are welcome! Please:
- Use a clear, descriptive title
- Provide detailed description of the proposed feature
- Explain why this enhancement would be useful
- Include mockups or examples if applicable

### Pull Requests

1. **Create a Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make Your Changes**
   - Follow the existing code style
   - Add comments for complex logic
   - Update documentation as needed

3. **Test Your Changes**
   ```bash
   mvn clean package
   java -jar target/scrcpy-gui-1.2.0-jar-with-dependencies.jar
   ```
   
   Test with various scrcpy configurations to ensure nothing breaks.

4. **Commit Your Changes**
   ```bash
   git add .
   git commit -m "feat: add your feature description"
   ```
   
   Use conventional commit messages:
   - `feat:` - New feature
   - `fix:` - Bug fix
   - `docs:` - Documentation changes
   - `style:` - Code style changes (formatting, etc.)
   - `refactor:` - Code refactoring
   - `test:` - Adding tests
   - `chore:` - Maintenance tasks

5. **Push and Create PR**
   ```bash
   git push origin feature/your-feature-name
   ```
   
   Then create a Pull Request on GitHub with:
   - Clear description of changes
   - Reference to related issues
   - Screenshots for UI changes

## Code Style Guidelines

### Java Code Style

- **Indentation**: 4 spaces (no tabs)
- **Line Length**: Maximum 120 characters
- **Naming Conventions**:
  - Classes: `PascalCase`
  - Methods: `camelCase`
  - Variables: `camelCase`
  - Constants: `UPPER_SNAKE_CASE`
  - Packages: `lowercase`

- **Documentation**: Add JavaDoc comments for public classes and methods

### Example:
```java
/**
 * Builds the scrcpy command from configuration
 */
public class CommandBuilder {
    private static final String SCRCPY_COMMAND = "scrcpy";
    
    private ScrcpyConfig config;
    
    /**
     * Creates a new CommandBuilder
     * @param config The scrcpy configuration
     */
    public CommandBuilder(ScrcpyConfig config) {
        this.config = config;
    }
    
    // ... rest of implementation
}
```

## Project Structure

```
gui/
├── src/main/java/com/scrcpy/gui/
│   ├── ScrcpyGUI.java              # Main application
│   ├── config/                      # Configuration management
│   ├── core/                        # Core functionality
│   └── panels/                      # UI panels
├── resources/                       # Resource files
├── pom.xml                          # Maven configuration
└── README.md                        # Project documentation
```

## Adding New Features

When adding new scrcpy options:

1. **Update ScrcpyConfig.java**: Add fields with getters/setters
2. **Update CommandBuilder.java**: Add command-line argument building logic
3. **Update appropriate Panel**: Add UI controls in the relevant panel
4. **Test**: Verify the generated command is correct

## Questions?

If you have questions:
- Check existing documentation
- Search closed issues
- Open a new issue with the "question" label

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

---

**Thank you for contributing!** 🎉

*Made with Love by Bennerdoo* ❤️
