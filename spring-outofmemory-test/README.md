# OutOfMemoryError調査用

## 起動時
$ docker build . -t spring-outofmemory-test
$ docker run -p 8080:8080 -v $PWD/logs/:/logs --memory=256m --memory-swap=256m -d spring-outofmemory-test
$ docker exec -it (起動されたコンテナID) /bin/bash

## 確認
$ curl "http://localhost:8080/bigobject?size=2"
