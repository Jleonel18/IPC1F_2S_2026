@echo off
if not exist out mkdir out
dir /s /b src\demo\*.java > sources.txt
javac -d out @sources.txt
del sources.txt
if %errorlevel%==0 java -cp out demo.controller.Main
pause
