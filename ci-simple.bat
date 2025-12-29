@echo off
echo === Study Platform CI Script ===

echo 1. Cleaning and building...
call mvn clean compile

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Build failed!
    exit /b 1
)

echo 2. Running tests...
call mvn test

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Tests failed!
    exit /b 1
)

echo 3. Building JAR...
call mvn package -DskipTests

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Package failed!
    exit /b 1
)

echo ✅ CI passed! Build successful.