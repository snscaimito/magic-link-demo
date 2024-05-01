FROM nginx:1.26.0-alpine

RUN apk add --no-cache gettext

ARG API_HOST
ENV API_HOST=${API_HOST}

COPY nginx.conf /etc/nginx/nginx.conf.template
COPY content /usr/share/nginx/html

EXPOSE 80

CMD envsubst '$$API_HOST' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf && nginx -g 'daemon off;'
