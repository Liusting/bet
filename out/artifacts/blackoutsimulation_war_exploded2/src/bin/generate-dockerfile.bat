chcp 65001
@echo off

set appName=%1
set appVersion=%2
set isSkipAllatori=%3
set appFullName=%1-%2
set DockerRoot="Dockerfile"
set lib="%DockerRoot%/lib"

echo.
echo 	==========================================================================================================================
echo 	...............................................................
echo 	.                                                             .
echo 	. generate %DockerRoot% folder
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

rd /s /q "%DockerRoot%"
md "%DockerRoot%"
md "%DockerRoot%/%appName%"
robocopy /np "%appFullName%/BOOT-INF/lib" %lib%
robocopy /S /np "classes" "%DockerRoot%/%appName%/classes"
rd /s /q "%DockerRoot%/%appName%/classes/mapper"
rd /s /q "%DockerRoot%/%appName%/classes/com"
robocopy /S /np "%appFullName%" "%DockerRoot%/%appName%/%appFullName%"
rd /s /q "%DockerRoot%/%appName%/%appFullName%/BOOT-INF/classes"
rd /s /q "%DockerRoot%/%appName%/%appFullName%/BOOT-INF/lib"

if "%isSkipAllatori%"=="true" (
   copy "%appFullName%.jar" "%DockerRoot%\%appName%"
) else (
   copy "obfuscated\%appFullName%.jar" "%DockerRoot%\%appName%"
)


robocopy "obfuscated" "Dockerfile/lib"

set jars="%appName%/%appFullName%.jar",

for /R %lib% %%f in (*.jar) do (
    FOR /F "delims=" %%i IN ("%%f") DO (
    call set jars=%%jars%%"lib/%%~ni%%~xi",
    )
)

ECHO %jars:~0%

set dFile="%DockerRoot%/%appName%/Dockerfile"
echo FROM openjdk:8-jre-alpine>>%dFile%
echo.>>%dFile%
echo ADD %appName%/%appFullName% /home/%appFullName%>>%dFile%
echo.>>%dFile%
echo ADD %appName%/classes /home/%appFullName%/BOOT-INF/classes>>%dFile%
echo.>>%dFile%
echo COPY [%jars%"/home/%appFullName%/BOOT-INF/lib/"]>>%dFile%
echo.>>%dFile%
echo CMD [ "sh", "-c", "cd /home/%appFullName%;java -Duser.timezone=CET -Dfile.encoding=UTF-8 -Dspring.config.additional-location=application.yml org.springframework.boot.loader.JarLauncher" ]>>%dFile%
