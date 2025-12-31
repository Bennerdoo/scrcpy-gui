# Automated GitHub Push at Midnight 2026

Complete guide for scheduling an automatic push to GitHub at exactly 00:00:00 on January 1st, 2026.

## Overview

This setup will automatically commit all changes and push your Scrcpy GUI project to GitHub at midnight to celebrate the New Year 2026! 🎉

## Files Created

- **`auto-push-github.ps1`** - PowerShell script that performs the git operations
- **`auto-push-github.bat`** - Batch wrapper for Task Scheduler
- **`push-log.txt`** - Log file (created automatically when script runs)

## Prerequisites

Before scheduling the task, you need to:

### 1. Create GitHub Repository

1. Go to [GitHub](https://github.com)
2. Click **New Repository** (green button)
3. Repository name: `scrcpy-gui` (or your preferred name)
4. Description: "Java Swing GUI for scrcpy - Made with Love by Bennerdoo ❤️"
5. Choose **Public** or **Private**
6. **DO NOT** initialize with README (you already have one)
7. Click **Create repository**

### 2. Configure Git Remote

In your project directory, run:

```bash
cd c:\Users\Bennerdo\OneDrive\Documents\PROJECTS\scrcpy-master\gui
git remote add origin https://github.com/YOUR_USERNAME/scrcpy-gui.git
```

Replace `YOUR_USERNAME` with your actual GitHub username.

### 3. Set Up Git Credentials

**Option A: Credential Manager (Recommended)**

```bash
git config --global credential.helper manager-core
```

Then do a test push - Windows will store your credentials:

```bash
git push -u origin main
```

Enter your GitHub credentials when prompted. They'll be saved securely.

**Option B: Personal Access Token**

1. Go to GitHub → Settings → Developer settings → Personal access tokens
2. Generate new token (classic)
3. Select scopes: `repo` (full control)
4. Copy the token
5. Use token as password when prompted

### 4. Test the Script Manually

Before scheduling, test that everything works:

```bash
cd c:\Users\Bennerdo\OneDrive\Documents\PROJECTS\scrcpy-master\gui
.\auto-push-github.bat
```

Check `push-log.txt` for results.

## Scheduling with Windows Task Scheduler

### Step 1: Open Task Scheduler

1. Press `Win + R`
2. Type: `taskschd.msc`
3. Press Enter

### Step 2: Create New Task

1. Click **Action → Create Task** (not "Create Basic Task")
2. **General Tab:**
   - Name: `GitHub Push - New Year 2026`
   - Description: `Automatically push Scrcpy GUI to GitHub at midnight`
   - Select: **Run whether user is logged on or not**
   - Check: **Run with highest privileges**
   - Configure for: **Windows 10**

### Step 3: Configure Trigger

1. Go to **Triggers** tab
2. Click **New**
3. Configure:
   - Begin the task: **One time**
   - Start date: **1/1/2026**
   - Start time: **12:00:00 AM** (00:00:00)
   - Check: **Enabled**
4. Click **OK**

### Step 4: Configure Action

1. Go to **Actions** tab
2. Click **New**
3. Configure:
   - Action: **Start a program**
   - Program/script: `C:\Users\Bennerdo\OneDrive\Documents\PROJECTS\scrcpy-master\gui\auto-push-github.bat`
   - Start in: `C:\Users\Bennerdo\OneDrive\Documents\PROJECTS\scrcpy-master\gui`
4. Click **OK**

### Step 5: Configure Conditions

1. Go to **Conditions** tab
2. **Power:**
   - Uncheck: **Start the task only if the computer is on AC power**
   - Uncheck: **Stop if the computer switches to battery power**
3. **Network:**
   - Check: **Start only if the following network connection is available**
   - Select: **Any connection**

### Step 6: Configure Settings

1. Go to **Settings** tab
2. Configure:
   - Check: **Allow task to be run on demand**
   - Check: **Run task as soon as possible after a scheduled start is missed**
   - If running task fails: **Do not restart**
3. Click **OK**

### Step 7: Save and Verify

1. Click **OK** to save the task
2. Enter your Windows password if prompted
3. Find your task in the list: `GitHub Push - New Year 2026`
4. Verify:
   - Status: **Ready**
   - Next Run Time: **1/1/2026 12:00:00 AM**

## Testing the Scheduled Task

### Test Run Now

1. Right-click the task
2. Click **Run**
3. Watch the command window appear
4. Check `push-log.txt` for results
5. Verify your GitHub repository was updated

### View Task History

1. Right-click the task
2. Click **Properties**
3. Go to **History** tab
4. See execution results

## What the Script Does

1. **Changes to project directory**
2. **Initializes git** (if not already initialized)
3. **Configures git user** (if not configured)
4. **Stages all changes** (`git add .`)
5. **Commits** with message: "🎉 New Year 2026 Release - Scrcpy GUI v1.1.0"
6. **Checks remote** repository is configured
7. **Pushes to GitHub** on the `main` branch
8. **Logs everything** to `push-log.txt`
9. **Plays success sound** (if successful)

## Customization

### Change Commit Message

Edit `auto-push-github.ps1`, line 7:

```powershell
[string]$CommitMessage = "Your custom message here"
```

### Change Branch

Edit `auto-push-github.ps1`, line 8:

```powershell
[string]$Branch = "your-branch-name"
```

### Change Email in Git Config

Edit `auto-push-github.ps1`, line 41:

```powershell
git config user.email "your-actual-email@example.com"
```

## Troubleshooting

### Task doesn't run at scheduled time

- **Computer must be on** at midnight
- **Internet must be connected**
- Check **Conditions** tab settings
- Verify task is **Enabled**

### Push fails - Authentication Error

```
ERROR: Push failed!
remote: Invalid username or password
```

**Solution:**
- Re-run credential setup
- Use Personal Access Token instead of password
- Check token has `repo` permissions

### Push fails - No Remote

```
WARNING: No remote repository configured!
```

**Solution:**
```bash
cd c:\Users\Bennerdo\OneDrive\Documents\PROJECTS\scrcpy-master\gui
git remote add origin https://github.com/YOUR_USERNAME/scrcpy-gui.git
```

### Task runs but nothing happens

- Check `push-log.txt` for error details
- Verify PowerShell execution policy allows scripts
- Run manually first to test: `.\auto-push-github.bat`

## Important Reminders

⚠️ **Computer Requirements:**
- Computer must be **ON** at midnight (or set to wake from sleep)
- Internet connection must be **ACTIVE**
- Windows **NOT** shutting down or restarting

💡 **Backup Plan:**
If computer is off, the task will run when you turn it on (if "run as soon as possible after missed start" is checked)

🔒 **Security:**
- Git credentials are stored securely by Windows Credential Manager
- Personal Access Tokens are recommended over passwords
- Never share your access token

## After Midnight Push

### Verify Success

1. Check `push-log.txt`:
   ```
   ✅ SUCCESS! Pushed to GitHub at 2026-01-01 00:00:XX
   🎉 Happy New Year 2026! 🎉
   ```

2. Visit your GitHub repository:
   ```
   https://github.com/YOUR_USERNAME/scrcpy-gui
   ```

3. Verify files are there:
   - Source code
   - README.md
   - LICENSE
   - All documentation

### Celebrate! 🎉

Your project is now live on GitHub to start the new year!

### Share Your Project

Add repository URL to your profile, LinkedIn, resume, etc.:
```
https://github.com/YOUR_USERNAME/scrcpy-gui
```

## Cleanup (After Success)

Once confirmed successful, you can:

1. **Disable the task** (right-click → Disable) so it doesn't try to run again
2. **Delete the task** if you won't need it again
3. **Keep the scripts** - you can reuse them for future scheduled pushes

---

**Made with Love by Bennerdoo** ❤️

**Happy New Year 2026!** 🎉🎊
