@echo off
setlocal enableextensions
pushd %~dp0

cd ..
call gradlew clean shadowJar

cd build\libs
set jarloc=
for /f "tokens=*" %%a in ('dir /b *.jar') do (
    set jarloc=%%a
)

java -jar %jarloc% < ..\..\text-ui-test\input.txt > ..\..\text-ui-test\ACTUAL.TXT 2>&1

cd ..\..\text-ui-test

:waitForFile
2>nul (>>ACTUAL.TXT (call )) && (goto :compare) || (
    timeout /t 1 /nobreak >nul
    goto :waitForFile
)

:compare
FC /W ACTUAL.TXT EXPECTED.TXT >NUL && (
    ECHO Test passed!
) || (
    ECHO Test failed!
    exit /b 1
)

popd