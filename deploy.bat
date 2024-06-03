@echo off
setlocal enabledelayedexpansion

for %%i in ("%~dp0") do set "current=%%~fi"
set "bin=%current%\bin"
set "lib=%current%\lib"
set "src=%current%\src"
set "temp=%current%\src\temp"

if not exist "%temp%" mkdir "%temp%"

for /r "%src%" %%f in (*.java) do copy "%%f" "%temp%"

javac -d "%bin%" -cp "%lib%\*" "%temp%\*.java"

rd /s /q "%temp%"

set "src_dir=bin"
set "web_dir=web"
set "lib_dir=lib"
set "assets_dir=assets"
set "config_dir=config"
set "target_name=Abeona"

@REM Eto ilay chemin makany amin'ny webapps anareo no apetraka 
set "target_dir=C:\chemin\vers\tomcat\webapps"

mkdir "%temp%\WEB-INF"
mkdir "%temp%\WEB-INF\classes"
mkdir "%temp%\WEB-INF\lib"
mkdir "%temp%\WEB-INF\views"
mkdir "%temp%\assets"

xcopy /s /e /i "%lib_dir%\*" "%temp%\WEB-INF\lib\"
xcopy /s /e /i "%src_dir%\*" "%temp%\WEB-INF\classes\"
xcopy /s /e /i "%web_dir%\*" "%temp%\WEB-INF\views\"
copy "%web_dir%\*.jsp" "%temp%\"
xcopy /s /e /i "%config_dir%\*" "%temp%\WEB-INF\"
xcopy /s /e /i "%assets_dir%\*" "%temp%\assets\"

jar -cf "%target_name%.war" -C "%temp%" .

copy "%target_name%.war" "%target_dir%"

del "%target_name%.war"
rd /s /q "%temp%"
