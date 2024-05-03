#!/bin/bash

export API_HOST=$(ifconfig en0 | grep inet | awk '$1=="inet" {print $2}')

tmux new-session -d -s magic-link-demo 'cd magic-link-api; ./mvnw spring-boot:run; read'
tmux new-window -t magic-link-demo:1 'docker compose --profile development up magic-link-web-dev --build; read'
tmux new-window -t magic-link-demo:2 'cd cypress; npx cypress open'

# Enable mouse support
tmux set-option -g mouse on

tmux attach-session -t magic-link-demo
