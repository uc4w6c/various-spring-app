### ヘルスチェック

```
$ curl localhost:8080/actuator/health | jq
```

### サーキットブレーカーのイベント
```
$ curl localhost:8080/actuator/circuitbreakerevents | jq
```
