
## リリース
aws lambda create-function --function-name sample fileb://apigateway-0.0.1-SNAPSHOT.jar --handler org.springframework.cloud.function.adapter.aws.FunctionInvoker --description "sample application"  --runtime java11 --region ap-northeast-1 --timeout 30 --memory-size 1024 --publish

org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest
