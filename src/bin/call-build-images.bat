chcp 65001
@echo off

set appName=%1

echo.
echo 	==========================================================================================================================
echo 	...............................................................
echo 	.                                                             .
echo 	. call build-%appName%.bat
echo 	.                                                             .
echo 	...............................................................
echo.
echo.
echo 	Current Directory : %cd%
echo.
echo.
echo 	==========================================================================================================================
echo.
echo.
echo.

set old_path=%cd%

cd ./target/Dockerfile
docker build -t di/%appName% -f %appName%/Dockerfile .
cd ../../src/config
docker build -t di/%appName%:1.0.0 .