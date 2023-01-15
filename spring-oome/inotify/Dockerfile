FROM alpine:3.17.0 as builder

RUN apk update
RUN apk add inotify-tools

RUN mkdir -p /var/log/jvmlogs

COPY main.sh /bin/main.sh
RUN chmod +x /bin/main.sh

FROM builder
CMD /bin/main.sh
