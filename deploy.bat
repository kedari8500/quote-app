@echo off
set DEPLOY_DIR=C:\deploy\quoteapp
if not exist %DEPLOY_DIR% mkdir %DEPLOY_DIR%

rem Stop the old version if something is listening on port 9100
for /f "tokens=5" %%a in ('netstat -aon ^| findstr ":9100" ^| findstr "LISTENING"') do taskkill /F /PID %%a

copy /Y target\quoteapp.jar %DEPLOY_DIR%\quoteapp.jar
powershell -NoProfile -ExecutionPolicy Bypass -File start-app.ps1
exit /b 0
