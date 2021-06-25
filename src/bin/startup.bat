@echo off

for %%* in (.) do set CurrDirName=%%~nx*

title %CurrDirName%

echo.
echo 	==========================================================================================================================
echo 	...............................................................
echo 	.                                                             .
echo 	. Web Name : %CurrDirName%
echo 	.                                                             .
echo 	...............................................................
echo.
echo.
echo 	Current Directory : %cd%
echo.
echo 	Run Command : java -Dspring.config.location=application.yml org.springframework.boot.loader.JarLauncher
echo.
echo 	Profile : %cd%\application.yml&
echo.
echo 	==========================================================================================================================
echo.
echo.
echo.

cd /d %cd%

java -Dspring.config.additional-location=application.yml org.springframework.boot.loader.JarLauncher

If not %errorlevel%==goto Error
:Error
pause