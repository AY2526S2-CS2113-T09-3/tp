@echo off
setlocal enableextensions

taskkill /F /IM java.exe /T 2>nul

cd ..
call gradlew clean shadowJar
if errorlevel 1 (
    echo Gradle build failed!
    exit /b 1
)

cd build\libs
set jarloc=
for /f "tokens=*" %%a in ('dir /b *.jar') do set jarloc=%%a

cd ..\..\text-ui-test


type input.txt | java -jar ..\build\libs\%jarloc% > ACTUAL.TXT 2>nul

timeout /t 5 /nobreak >nul

for %%A in (ACTUAL.TXT) do if %%~zA equ 0 (
    echo [ERROR] ACTUAL.TXT is empty. The Windows pipe failed.
    exit /b 1
)

fc ACTUAL.TXT EXPECTED.TXT > nul
if errorlevel 1 (
    echo [FAIL] Output does not match expected!
    echo --- ACTUAL OUTPUT ---
    type ACTUAL.TXT
    echo ----------------------
    exit /b 1
) else (
    echo [PASS] Test passed!
    exit /b 0
)