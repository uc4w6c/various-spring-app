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

作成後/
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
http://localhost:8080/oauth2/authorize?response_type=code&scope=openid%20profile&client_id=messaging-client&redirect_uri=http%3A%2F%2F127.0.0.1%3A8080%2Flogin%2Foauth2%2Fcode%2Fmessaging-client-oidc

OAuth2AuthorizationEndpointFilter
->
OAuth2AuthorizationCodeRequestAuthenticationProvider

http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc?code=yaVFTg8oWhCXYh_KVWiIeXD3Nc9KQ1mv7SVFAzHZGbFsxbE3u3L2KE-iim8JOj-xArfx5ITUSRrXhyP8CVmXoyLJYYM8-29JJYSUMyRWuoZ19L0IYUnvWyA4ak_pQdIm&continue

ihlbuYDXQnxCoXDSMEJmmlcaLJuYbgksa1YHM17x3IpeJe9vUqVnFX9RuVW-5E3Y3yilQKemlEyeaU7k50-CPTNkXthWRWL_ZWJq_yG219tK_yHbGDN6YXiH6ijXKUb3
# -H 'Authorization: Bearer eyJraWQiOiI4OTRjNjUzMi01YWYwLTRjNDUtYjg4ZC0zYmZiYzY1Y2FhOTQiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtZXNzYWdpbmctY2xpZW50IiwiYXVkIjoibWVzc2FnaW5nLWNsaWVudCIsIm5iZiI6MTY3NTM5MTcwMiwic2NvcGUiOlsibWVzc2FnZS5yZWFkIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCIsImV4cCI6MTY3NTM5MjAwMiwiaWF0IjoxNjc1MzkxNzAyLCJ0b2tlbiI6IjkyMTk4OTkyLWMwY2MtNGM5My1hYjZkLTg5NGZhYzEyNGUxNyJ9.Z1T1FLHypRdofdi5w9g32ieQDS-1-9nZh80Ga1OB2G9IpM9B4uDMjjDi9G_kvmAtl81CtcBqU7eQgUWKwDb5z0AnKUAG9o7jIV2Mtm-k1uZOb8G7_bhcSNbNw7D7Lc2AX7ZzEyB_vbSx_GN2zR6_1c4A-4bISMXemV95GCePjdBVL179-Be3WL3vGdZmVR2EkTewIYRMf5jjFqPY0jVGzgYixD63NyEvf6ORIeKE3mRC1Wtphl_5v605PgI5chDlRKVEcENYVZk4IN3-JH1r5bqg1Kf3c3rwLi0vfLvtX40aB3RzUnGx032S1twDc70MgGfE9DaCIhhLZFAEu0mkig' \

$ curl -X POST -v "http://localhost:8080/oauth2/token" -H 'Content-Type: application/x-www-form-urlencoded' -u messaging-client:secret -d "grant_type=authorization_code&redirect_uri=http%3A%2F%2F127.0.0.1%3A8080%2Flogin%2Foauth2%2Fcode%2Fmessaging-client-oidc&code=rTxICQl46NpzzaOkScs8tu1__SDvv9ysZiyI0F6PWW-kH3CdZqd9i0s3nGXAprtZXTlF6hzZwhruqaYQ9AobC1FUTlJaB8nDYwqeZTV3w4SjBhF-xeCwVPvioNFt-nDR"

結果

```
{
  "kid": "894c6532-5af0-4c45-b88d-3bfbc65caa94",
  "alg": "RS256"
}
{
"sub": "user",
"aud": "messaging-client",
"nbf": 1675393012,
"scope": [
"openid",
"profile"
],
"iss": "http://localhost:8080",
"exp": 1675393312,
"iat": 1675393012,
"token": "78015c1d-bde3-401a-b2f1-a77f530d8ed0"
}
```

authorizeは
OAuth2AuthorizationCodeRequestAuthenticationConverterでユーザーの情報取得
OAuth2AuthorizationCodeRequestAuthenticationProvider

tokenは
ユーザーの認可トークン認証はOAuth2AuthorizationCodeAuthenticationProviderで実施


ユーザーのときはOAuth2AuthorizationのprincipalNameにはuserがセットされる


## 公開鍵情報を取得
http://localhost:8080/oauth2/jwks

## アクセストークンの妥当性チェック
http://localhost:8080/oauth2/introspect
参考: https://qiita.com/minamijoyo/items/bd76ce780e4551d7c951

## アクセストークンの破棄
http://localhost:8080/oauth2/revoke

## URL一覧確認
http://localhost:8080/.well-known/oauth-authorization-server
