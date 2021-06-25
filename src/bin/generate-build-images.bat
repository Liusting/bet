chcp 65001
@echo off

set appName=%1

echo.
echo 	==========================================================================================================================
echo 	...............................................................
echo 	.                                                             .
echo 	. generate build images script
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

set bFile="Dockerfile/build-%appName%.bat"
echo cd /d %%cd%%>%bFile%
echo.>>%bFile%
echo docker build -t di/%appName% -f %appName%/Dockerfile .>>%bFile%
echo.>>%bFile%
echo echo docker tag di/%appName% di/%appName%[:version]>>%bFile%
echo pause>>%bFile%

set bFile4Linux="Dockerfile/build-%appName%.sh"
echo #!/usr/bin/env bash>%bFile4Linux%
echo echo vi build-%appName%.sh>>%bFile4Linux%
echo echo :set fileformat=unix>>%bFile4Linux%
echo echo :wq>>%bFile4Linux%
echo.>>%bFile4Linux%
echo cd `dirname $0`>>%bFile4Linux%
echo.>>%bFile4Linux%
echo docker build -t di/%appName% -f %appName%/Dockerfile .>>%bFile4Linux%
echo.>>%bFile4Linux%
echo echo docker tag di/%appName% di/%appName%[:version]>>%bFile4Linux%