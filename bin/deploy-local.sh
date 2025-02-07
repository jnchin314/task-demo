#!/bin/bash

# Set variables
PROJECT_NAME="your-project-name"
IMAGE_NAME="unemploymenttracker"
DOCKER_COMPOSE_FILE="compose.yaml"

# Build Maven project
echo "Building Maven project..."
pwd
./mvnw clean package -DskipTests

# Check if Maven build was successful
if [ $? -ne 0 ]; then
  echo "Maven build failed."
  exit 1
fi

# Build Docker image
echo "Building Docker image..."

docker compose build --no-cache

# Check if Docker build was successful
if [ $? -ne 0 ]; then
  echo "Docker image build failed."
  exit 1
fi

# Run Docker Compose
echo "Running Docker Compose..."
docker-compose -f ${DOCKER_COMPOSE_FILE} up -d

# Check if Docker Compose ran successfully
if [ $? -ne 0 ]; then
  echo "Docker Compose failed."
  exit 1
fi

echo "Build, image creation, and Docker Compose completed successfully."