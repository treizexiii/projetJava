#!/bin/bash

find -name "*.java" > ./build/sources.txt
javac @./build/sources.txt -d ./webapp/WEB-INF/classes -classpath lib/\*


# javac @sources.txt -d ./WEB-INF/classes -classpath ./lib/servlet.jar 