@echo off
REM Batch wrapper to run PowerShell script with execution policy bypass
REM This is needed for Windows Task Scheduler

cd /d "%~dp0"

echo ========================================
echo Automated GitHub Push - New Year 2026
echo ========================================
echo.
echo Starting at %date% %time%
echo.

REM Run PowerShell script with bypass execution policy
powershell.exe -ExecutionPolicy Bypass -File "%~dp0auto-push-github.ps1"

echo.
echo Script completed at %date% %time%
echo.
echo Check push-log.txt for details
echo.
pause
