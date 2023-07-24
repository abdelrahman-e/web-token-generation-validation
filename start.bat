@echo off
REM Start the GeneratorService application
cd generator
start mvnw spring-boot:run
cd ..
REM Start the ValidatorService application
cd validator
start mvnw spring-boot:run
cd ..
REM Start the frontend application
cd frontend
start npm start
cd ..
