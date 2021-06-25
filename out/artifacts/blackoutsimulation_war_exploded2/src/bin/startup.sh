#!/bin/bash
CRTDIR=$(pwd)
CurrDirName=$(basename `pwd`)

echo -e
echo "	=========================================================================================================================="
echo "	..............................................................."
echo "	.                                                             ."
echo "	. Web Name : $CurrDirName"
echo "	.                                                             ."
echo "	..............................................................."
echo -e
echo -e
echo "	Current Directory : $CRTDIR"
echo -e
echo "	Run Command : java -Dspring.config.additional-location=application.yml org.springframework.boot.loader.JarLauncher"
echo -e
echo "	Profile : $CRTDIR\application.yml"
echo -e
echo "	=========================================================================================================================="
echo -e
echo -e
echo -e

cd $CRTDIR

java -Dspring.config.additional-location=application.yml org.springframework.boot.loader.JarLauncher
