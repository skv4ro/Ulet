@echo off
set PRO_LANG=english
set CREO_R_DIR=C:\applic\creo7
set ST_DIR=%APPDATA%\avsys\creo
set STARTUP_DIR=%ST_DIR%\startup
set REMOTE_DIR=c:\users\skvarkaj\documents\ulet
set TEMP=%ST_DIR%\temp
:
if not exist %STARTUP_DIR%\nul mkdir %STARTUP_DIR%
:
del %STARTUP_DIR%\*.ui
del %STARTUP_DIR%\*.pro
:
echo Copying new files....
echo.
:
copy /y %REMOTE_DIR%\config\config.pro %STARTUP_DIR%\config.pro
copy /y %REMOTE_DIR%\config\creo_parametric_customization.ui %STARTUP_DIR%\creo_parametric_customization.ui
:
echo.
echo All done!
echo Creo Parametric 7.0.1.0 Starting
:
cd /d %STARTUP_DIR%
start "" "%CREO_R_DIR%\Creo 7.0.1.0\Parametric\bin\parametric.exe"
ping 127.0.0.1 -n 4 > nul
:
echo exit
exit