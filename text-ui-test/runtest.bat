@echo off
setlocal enableextensions

taskkill /F /IM java.exe /T 2>nul


cd ..
call gradlew clean shadowJar --no-daemon
if errorlevel 1 (
    echo [ERROR] Build failed
    exit /b 1
)

cd build\libs
set "TARGET_JAR="
for /f "tokens=*" %%a in ('dir /b *.jar') do set "TARGET_JAR=%%a"

timeout /t 5 /nobreak >nul

cd ..\..\text-ui-test


if exist ACTUAL.TXT del /f /q ACTUAL.TXT

echo [RUNNING] Testing with %TARGET_JAR%...

powershell -Command "Get-Content input.txt | java -jar '..\build\libs\%TARGET_JAR%' | Out-File -FilePath 'ACTUAL.TXT' -Encoding ascii"

timeout /t 3 /nobreak >nul


fc /L /W ACTUAL.TXT EXPECTED.TXT > nul

if %errorlevel% EQU 0 (
    echo [PASS] Test passed!
    exit /b 0
) else (
    echo [FAIL] Output mismatch!
    echo --- ACTUAL ---
    if exist ACTUAL.TXT (type ACTUAL.TXT) else (echo File not found!)
    echo --- EXPECTED ---
    type EXPECTED.TXT
    exit /b 1
)