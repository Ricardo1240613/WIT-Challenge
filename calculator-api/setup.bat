@echo off
cd /d "%~dp0"
docker-compose up -d
call mvnw clean install
start cmd /k "cd calculator && ..\mvnw spring-boot:run"
start cmd /k "cd rest && ..\mvnw spring-boot:run"
