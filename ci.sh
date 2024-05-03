#!/bin/bash

export API_HOST=$(ifconfig en0 | grep inet | awk '$1=="inet" {print $2}')

# Start the two programs in the background
tmux new-session -d -s magic-link-demo 'cd magic-link-api; ./mvnw spring-boot:run'
tmux new-window -t magic-link-demo:1 'docker compose --profile development up magic-link-web-dev --build'

# Wait for the Spring Boot application to start
while ! nc -z localhost 8080; do
  sleep 1
done

# Wait for the Docker container to be ready
while ! nc -z localhost 80; do
  sleep 1
done

# Run Cypress
tmux new-window -t magic-link-demo:2 'pushd cypress; npx cypress run; popd; read'

tmux set-option -g mouse on
tmux attach-session -t magic-link-demo