# Automated GitHub Push Script
# This script will commit all changes and push to GitHub
# Scheduled to run at 00:00:00 on January 1st, 2026

param(
    [string]$RepoPath = "c:\Users\Bennerdo\OneDrive\Documents\PROJECTS\scrcpy-master\gui",
    [string]$CommitMessage = "🎉 New Year 2026 Release - Scrcpy GUI v1.1.0",
    [string]$Branch = "main"
)

# Log file for debugging
$LogFile = Join-Path $RepoPath "push-log.txt"

function Write-Log {
    param([string]$Message)
    $timestamp = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
    $logMessage = "[$timestamp] $Message"
    Write-Host $logMessage
    Add-Content -Path $LogFile -Value $logMessage
}

try {
    Write-Log "========================================="
    Write-Log "Starting automated GitHub push script"
    Write-Log "========================================="
    
    # Change to repository directory
    Set-Location -Path $RepoPath
    Write-Log "Changed directory to: $RepoPath"
    
    # Check if git repository exists
    if (-not (Test-Path ".git")) {
        Write-Log "ERROR: Not a git repository!"
        Write-Log "Initializing git repository..."
        git init
        Write-Log "Git repository initialized"
    }
    
    # Configure git user if not set
    $gitUser = git config user.name
    if ([string]::IsNullOrEmpty($gitUser)) {
        Write-Log "Configuring git user..."
        git config user.name "Bennerdoo"
        git config user.email "benardmartinotieno@gmail.com"
        Write-Log "Git user configured"
    }
    
    # Add all files
    Write-Log "Staging all changes..."
    git add .
    Write-Log "Files staged successfully"
    
    # Check if there are changes to commit
    $status = git status --porcelain
    if ([string]::IsNullOrEmpty($status)) {
        Write-Log "No changes to commit"
    } else {
        Write-Log "Changes detected, committing..."
        git commit -m "$CommitMessage"
        Write-Log "Commit successful"
    }
    
    # Check if remote exists
    $remote = git remote get-url origin 2>&1
    if ($LASTEXITCODE -ne 0) {
        Write-Log "WARNING: No remote repository configured!"
        Write-Log "Please run: git remote add origin <your-github-url>"
        Write-Log "Script will exit. Please configure remote and run manually."
        exit 1
    }
    
    Write-Log "Remote repository: $remote"
    
    # Push to GitHub
    Write-Log "Pushing to GitHub..."
    Write-Log "Branch: $Branch"
    
    git push -u origin $Branch 2>&1 | ForEach-Object { Write-Log $_ }
    
    if ($LASTEXITCODE -eq 0) {
        Write-Log "========================================="
        Write-Log "✅ SUCCESS! Pushed to GitHub at $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')"
        Write-Log "========================================="
        Write-Log ""
        Write-Log "🎉 Happy New Year 2026! 🎉"
        Write-Log "Made with Love by Bennerdoo ❤️"
        
        # Play a success sound (optional)
        [System.Media.SystemSounds]::Asterisk.Play()
    } else {
        Write-Log "========================================="
        Write-Log "❌ ERROR: Push failed!"
        Write-Log "========================================="
        Write-Log "Please check your internet connection and GitHub credentials"
        exit 1
    }
    
} catch {
    Write-Log "========================================="
    Write-Log "❌ EXCEPTION OCCURRED"
    Write-Log "========================================="
    Write-Log "Error: $($_.Exception.Message)"
    Write-Log "Stack Trace: $($_.ScriptStackTrace)"
    exit 1
}
