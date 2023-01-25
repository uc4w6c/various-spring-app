## クライアントクレデンシャル
$ curl -X POST "http://localhost:8080/oauth2/token" -H 'Content-Type: application/x-www-form-urlencoded' -u messaging-client:secret -d "grant_type=client_credentials&scope=message.read" -v

OAuth2ClientCredentialsAuthenticationProvider
トークン発行
DelegatingOAuth2TokenGenerator
JwtGenerator
