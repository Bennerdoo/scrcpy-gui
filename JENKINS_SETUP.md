# Jenkins Setup Guide for Scrcpy GUI

Complete guide for setting up Jenkins CI/CD automation for the Scrcpy GUI project.

## Prerequisites

Make sure you have the following installed on your Jenkins server:

- ✅ **Jenkins** (already installed locally)
- ✅ **Java 11+** (for running Jenkins and building the project)
- ✅ **Maven 3.6+** (for building Java projects)
- ✅ **Git** (for source control)
- ✅ **JDK 17+** (for jpackage to create Windows installer)

## Jenkins Configuration

### 1. Install Required Plugins

Go to **Jenkins → Manage Jenkins → Manage Plugins** and install:

- **Pipeline** (usually pre-installed)
- **Git Plugin** (usually pre-installed)
- **Email Extension Plugin** (for build notifications - optional)
- **Workspace Cleanup Plugin** (for cleaning artifacts)

### 2. Configure Tools

Go to **Jenkins → Manage Jenkins → Global Tool Configuration**:

#### Maven Configuration
1. Click **Add Maven**
2. Name: `Maven 3.9`
3. Version: Select latest Maven 3.9.x
4. Check "Install automatically"
5. Save

#### JDK Configuration
1. Click **Add JDK**
2. Name: `JDK 11`
3. Either:
   - Point to existing JDK installation, OR
   - Check "Install automatically" and select version
4. Save

### 3. Create Jenkins Job

#### Option A: Pipeline from SCM (Recommended)

1. Click **New Item**
2. Enter name: `Scrcpy-GUI-Build`
3. Select **Pipeline**
4. Click **OK**
5. Under **Pipeline** section:
   - Definition: **Pipeline script from SCM**
   - SCM: **Git**
   - Repository URL: `file:///c:/Users/Bennerdo/OneDrive/Documents/PROJECTS/scrcpy-master`
     - (Or your Git remote URL if you have one)
   - Branch: `*/main` (or your default branch)
   - Script Path: `gui/Jenkinsfile`
6. Click **Save**

#### Option B: Local Git Repository

If using local Git without remote:

```bash
cd c:\Users\Bennerdo\OneDrive\Documents\PROJECTS\scrcpy-master
git init
git add .
git commit -m "Initial commit with Jenkins pipeline"
```

Then in Jenkins job configuration, use:
- Repository URL: `file:///c:/Users/Bennerdo/OneDrive/Documents/PROJECTS/scrcpy-master`

### 4. Configure Build Triggers

In the job configuration, under **Build Triggers**:

**Manual Builds:**
- Leave all unchecked to build manually only

**Scheduled Builds:**
- Check **Build periodically**
- Schedule: `H 2 * * *` (nightly at 2 AM)
- Or: `H H * * 0` (weekly on Sunday)

**On Git Changes:**
- Check **Poll SCM**
- Schedule: `H/5 * * * *` (every 5 minutes)

### 5. Configure Email Notifications (Optional)

Go to **Jenkins → Manage Jenkins → Configure System**:

1. Scroll to **Extended E-mail Notification**
2. Configure SMTP server settings
3. Set default recipients: your email
4. Save

## Running Your First Build

### Manual Build

1. Go to your Jenkins job
2. Click **Build Now**
3. Watch the build progress in **Console Output**

### Expected Build Stages

1. **Checkout** - Clones the repository
2. **Build** - Compiles Java code with Maven
3. **Test** - Runs unit tests
4. **Package JAR** - Creates fat JAR with dependencies
5. **Package Windows EXE** - Creates Windows installer
6. **Archive Artifacts** - Stores build outputs

### Viewing Artifacts

After a successful build:

1. Go to the build (e.g., Build #1)
2. Click **Build Artifacts** on the left
3. Download:
   - `target/scrcpy-gui-1.2.0-jar-with-dependencies.jar`
   - `dist/ScrcpyGUI-1.2.0.exe`

## Troubleshooting

### Build Fails at Maven Stage

**Error:** `mvn: command not found`

**Solution:**
- Make sure Maven is configured in Global Tool Configuration
- Verify path in `PATH` environment variable

### Build Fails at Package EXE Stage

**Error:** `jpackage: command not found`

**Solution:**
- Install JDK 17+ (jpackage included since JDK 14)
- Add JDK bin to PATH: `C:\Program Files\Java\jdk-17\bin`

### Git Checkout Fails

**Error:** `Failed to connect to repository`

**Solution:**
- Verify repository path is correct
- Use `file:///` protocol for local repos
- Check Git is installed and in PATH

### Workspace Permission Issues

**Error:** `Permission denied`

**Solution:**
- Run Jenkins with appropriate permissions
- Check workspace directory permissions

## Pipeline Features

### Automatic Artifact Archiving

Build artifacts are automatically saved and available for download:
- JAR files from `target/`
- EXE installer from `dist/`

### Post-Build Actions

**On Success:**
- Sends success email notification
- Archives artifacts
- Cleans workspace

**On Failure:**
- Sends failure email with logs
- Preserves workspace for debugging

### Environment Variables

Available in pipeline:
- `PROJECT_NAME`: ScrcpyGUI
- `VERSION`: 1.2.0
- `BUILD_NUMBER`: Jenkins build number
- `BUILD_URL`: URL to build page

## Advanced Configuration

### Parallel Builds

To speed up builds, you can run tests and packaging in parallel:

```groovy
stage('Parallel Stage') {
    parallel {
        stage('Test') {
            steps {
                dir('gui') {
                    bat 'mvn test'
                }
            }
        }
        stage('Static Analysis') {
            steps {
                echo 'Running code analysis...'
            }
        }
    }
}
```

### Build Parameters

Add build parameters for flexible builds:

1. In job configuration, check **This project is parameterized**
2. Add **String Parameter**:
   - Name: `VERSION`
   - Default Value: `1.2.0`
   - Description: Version to build
3. Use in pipeline: `${params.VERSION}`

### GitHub Integration (If Using GitHub)

1. Install **GitHub Plugin**
2. Configure GitHub webhook in repository settings
3. In Jenkins job, check **GitHub hook trigger for GITScm polling**
4. Builds trigger automatically on push

## Monitoring Builds

### Build Status

- **Blue ball** ✅ - Success
- **Red ball** ❌ - Failure
- **Yellow ball** ⚠️ - Unstable
- **Gray ball** ⏸️ - Not built/aborted

### Build History

- View all builds in job page
- Click any build to see:
  - Console Output
  - Changes
  - Artifacts
  - Test Results (if available)

### Build Trends

Jenkins shows build trends over time:
- Success/failure rate
- Build duration
- Test pass rate

## Next Steps

1. ✅ Create the Jenkins job following this guide
2. ✅ Run your first build
3. ✅ Verify artifacts are created
4. 📧 Configure email notifications
5. 🔄 Set up automatic triggers (optional)
6. 🚀 Share the installer with users!

---

**Made with Love by Bennerdoo** ❤️
