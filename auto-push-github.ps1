# Automated GitHub Push Script - Simplified
# Commits all changes and pushes to GitHub at midnight 2026
# Remote: https://github.com/Bennerdoo/scrcpy-gui.git

param(
    [string]$RepoPath = "c:\Users\Bennerdo\OneDrive\Documents\PROJECTS\scrcpy-master\gui",
    [string]$CommitMessage = "🎉 New Year 2026 Release - Scrcpy GUI v1.2.0",
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
    Write-Log "🎆 New Year 2026 GitHub Push 🎆"
    Write-Log "========================================="
    Write-Log "Starting at: $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')"
    Write-Log ""
    
    # Change to repository directory
    Set-Location -Path $RepoPath
    Write-Log "✓ Changed to repository: $RepoPath"
    
    # Stage all changes
    Write-Log "📦 Staging all changes..."
    git add . 2>&1 | ForEach-Object { Write-Log "  $_" }
    Write-Log "✓ All files staged"
    
    # Commit changes
    Write-Log "✍️ Creating commit..."
    git commit -m "$CommitMessage" 2>&1 | ForEach-Object { Write-Log "  $_" }
    
    if ($LASTEXITCODE -eq 0) {
        Write-Log "✓ Commit successful"
    } else {
        Write-Log "⚠️ No changes to commit (already up to date)"
    }
    
    # Push to GitHub
    Write-Log "📤 Pushing to GitHub..."
    Write-Log "  Remote: https://github.com/Bennerdoo/scrcpy-gui.git"
    Write-Log "  Branch: $Branch"
    Write-Log ""
    
    git push -u origin $Branch 2>&1 | ForEach-Object { Write-Log "  $_" }
    
    if ($LASTEXITCODE -eq 0) {
        Write-Log ""
        Write-Log "========================================="
        Write-Log "✅ SUCCESS! Project is LIVE on GitHub!"
        Write-Log "========================================="
        Write-Log ""
        Write-Log "🎉 Happy New Year 2026! 🎉"
        Write-Log "🚀 View at: https://github.com/Bennerdoo/scrcpy-gui"
        Write-Log ""
        Write-Log "Made with Love by Bennerdoo ❤️"
        Write-Log "========================================="
        
        # Play a success sound
        [System.Media.SystemSounds]::Asterisk.Play()
    } else {
        Write-Log ""
        Write-Log "========================================="
        Write-Log "❌ ERROR: Push failed!"
        Write-Log "========================================="
        Write-Log "Common issues:"
        Write-Log "  - No internet connection"
        Write-Log "  - GitHub credentials not saved"
        Write-Log "  - Repository access denied"
        Write-Log ""
        Write-Log "To fix: Run 'git push' manually and save credentials"
        exit 1
    }
    
} catch {
    Write-Log ""
    Write-Log "========================================="
    Write-Log "❌ EXCEPTION OCCURRED"
    Write-Log "========================================="
    Write-Log "Error: $($_.Exception.Message)"
    Write-Log "Stack Trace: $($_.ScriptStackTrace)"
    exit 1
}
