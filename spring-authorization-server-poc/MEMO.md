## クライアントクレデンシャル
$ curl -X POST "http://localhost:8080/oauth2/token" -H 'Content-Type: application/x-www-form-urlencoded' -u messaging-client:secret -d "grant_type=client_credentials&scope=message.read" -v

OAuth2ClientCredentialsAuthenticationProvider
トークン発行
DelegatingOAuth2TokenGenerator
JwtGenerator


### Tokenに項目を追加
Tokenに項目を追加したい場合は `OAuth2TokenCustomizer<JwtEncodingContext>` のBeanを生成する
参考
https://spring.pleiades.io/spring-authorization-server/docs/current/reference/html/core-model-components.html#oauth2-token-customizer
https://spring.pleiades.io/spring-authorization-server/docs/current/reference/html/guides/how-to-userinfo.html#how-to-userinfo

### クライアントのと登録
これで行ける。Bearer にはクライアントクレデンシャルで取得したトークンを利用する
curl -X POST -v "http://localhost:8080/connect/register" -d '{ "client_id": "123", "redirect_uris": "http://127.0.0.1/callback" }' -H 'Content-Type: application/json' -H 'Authorization: Bearer eyJraWQiOiI5Y2Y1YTg3Ni0zM2M3LTQ5ZTMtODlkYy04OGIwM2NmYjA2ZjYiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtZXNzYWdpbmctY2xpZW50IiwiYXVkIjoibWVzc2FnaW5nLWNsaWVudCIsIm5iZiI6MTY3NTA0MTcxOCwic2NvcGUiOlsiY2xpZW50LmNyZWF0ZSJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJleHAiOjE2NzUwNDIwMTgsImlhdCI6MTY3NTA0MTcxOCwidG9rZW4iOiIyMTU0NzdjYi05Mzg1LTQ5OTctYjg3MC1kZmQ0MTc3MmVjNDcifQ.hPkIrnbz3cQsss4j4v0V-vyTi7Y3Fh2jHJLZce5HqtHVq1AlNZuDZueVL-jwzRDorsaHLK0l1nMhEOg_NgPkfCgieMTQYtTX-zxMCe5pGHM2-0WY7TaSNHum-gR4wFtOAKPbd3FPrzWpURL4cbAm8e236Bf29vzHXCxgBHej4ZIoWoASEX4b4SWcBoKXuGsFog9jLLFgYcExNnvS3T-BRvncx_2CVaGg5zhfBE6lQlDUDFR537jlTwtVhjVK16gh3Qdltd2CSRbut2BTnPFqiIi8AfGP4AEBbAROvSHZOS2ClmjoRweaFtBq38FtqiwluJchnhjvCqLQReG5B4FEdg'

/connect/register
OidcClientRegistrationEndpointFilterで実施している

各カラムは
OidcClientRegistrationHttpMessageConverterの
MapOidcClientRegistrationConverter

各項目
client_id
client_id_issued_at
client_secret
client_secret_expires_at
client_name
redirect_uris
token_endpoint_auth_method
token_endpoint_auth_signing_alg
grant_types
response_types
scope
jwks_uri
id_token_signed_response_alg
