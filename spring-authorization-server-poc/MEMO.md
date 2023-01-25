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
