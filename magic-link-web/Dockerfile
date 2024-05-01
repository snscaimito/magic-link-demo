FROM nginx:1.26.0-alpine

COPY nginx.conf /etc/nginx/nginx.conf
VOLUME /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
