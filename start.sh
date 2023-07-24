#!/bin/sh

cd generator
# Start the GeneratorService SpringBoot application
./mvnw spring-boot:run &
# Capture the process ID of the GeneratorService application
GENERATOR_PID=$!
cd ..

cd validator
# Start the ValidatorService SpringBoot application
./mvnw spring-boot:run &
# Capture the process ID of the ValidatorService application
VALIDATOR_PID=$!
cd ..

# Wait for 5 seconds before starting the frontend application
sleep 5

cd frontend
# Start the frontend React Next.js application
npm start &
cd ..

# Set up a trap to kill all processes when the script is closed
trap "kill $GENERATOR_PID $VALIDATOR_PID; npx kill-port 8080" SIGINT SIGTERM



# Wait for all processes to exit
wait $GENERATOR_PID $VALIDATOR_PID "$FRONTEND_PID"
