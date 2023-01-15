#!/bin/sh

LOGS_DIR=/var/log/jvmlogs

inotifywait -mq -r -e create ${LOGS_DIR} |
  while read path action file; do
      echo "$(date) ${path}${file} が更新されました"
  done
