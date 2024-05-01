#!/bin/bash
export API_HOST=`ifconfig en0 | grep inet | awk '$1=="inet" {print $2}'`

tmux new-session -d -s magic-link-demo 'cd magic-link-api; ./mvnw spring-boot:run; read'
tmux split-window -h -t magic-link-demo 'docker compose --profile development up magic-link-web-dev --build; read'
tmux attach-session -t magic-link-demo
