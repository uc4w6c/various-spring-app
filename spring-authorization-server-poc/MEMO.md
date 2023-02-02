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

### クライアントの登録
これで行ける。Bearer にはクライアントクレデンシャルで取得したトークンを利用する
curl -X POST -v "http://localhost:8080/connect/register" -d '{ "client_id": "123", "redirect_uris": "http://127.0.0.1/callback", "grant_types": ["authorization_code", "client_credentials"] }' -H 'Content-Type: application/json' -H 'Authorization: Bearer eyJraWQiOiIxNzU3NTc0Mi1kOTMwLTRkZTMtYTcxMC0zZmI4MzI1MmM1OTYiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtZXNzYWdpbmctY2xpZW50IiwiYXVkIjoibWVzc2FnaW5nLWNsaWVudCIsIm5iZiI6MTY3NTA2Mzg4NCwic2NvcGUiOlsiY2xpZW50LmNyZWF0ZSJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjkwMDAiLCJleHAiOjE2NzUwNjQxODQsImlhdCI6MTY3NTA2Mzg4NH0.OAu9wWM60Lo_3nSva5Q_SV4Orw2edgm__PjfclbtpfcdhYrjG07m10h7aZfmiZGduXjvueWnMTxkCmy_2UBB6pFffAnWUvOBaVxoQkqsG_z6e6ynaRrC6hUPm3oB0HH7OEwiSM4znjSUxm5cNmmuhGQkbx96oxhGb7Q-RXy0FbRRIQION19biqn8XK_AP9LwnFwi6Ks-GSheuPRV1IUKc7wyrtlglKys1kqbgKp5voRSXIfY7kdggJNT_tQlCm7weqWKNdIOfYcYoQrv0jupvFm34G9n2blNX2WrE9YhdLBR9sgVz-xIdXFSH_xzth8cTzAA_d-dBiOwq9LWt-NxlA'

作成後
$ curl -X POST "http://localhost:8080/oauth2/token" -H 'Content-Type: application/x-www-form-urlencoded' -u "2DQlplz2KXwoWadgioiGI9Bb84L_Ac5uAZKqtSBEYUw:O1funY-xKO0HmvnpsU4VpFLx5wctz___NaV0ZGZUNOi7BSaTzKcYQVk_8RuEMBEl" -d "grant_type=client_credentials" -v

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

## ユーザー認証
