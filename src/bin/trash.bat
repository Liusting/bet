chcp 65001
@echo off

set appFullName=%1-%2

echo.
echo 	==========================================================================================================================
echo 	...............................................................
echo 	.                                                             .
echo 	. trash
echo 	.                                                             .
echo 	...............................................................
echo.
echo.
echo 	Current Directory : %cd%
echo.
echo 	Web Name : %appFullName%
echo.
echo 	==========================================================================================================================
echo.
echo.
echo.

cd ./target
rd /s /q "obfuscated"
md "obfuscated"