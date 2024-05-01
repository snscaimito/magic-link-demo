#!/bin/bash
tmux new-session -d -s magic-link-demo 'cd magic-link-api; ./mvnw spring-boot:run; read'
tmux split-window -h -t magic-link-demo 'cd magic-link-web; docker build -t magic-link-web .; docker run -p 80:80 magic-link-web; read'
tmux attach-session -t magic-link-demo
