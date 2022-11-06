package com.example.springdocexplanation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "タイトル",
				description = "説明",
				termsOfService = "http://example.com/terms/",
				contact = @Contact(
						name = "名前",
						url = "http://www.example.com/support",
						email = "support@example.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0.html"
				),
				version = "1.0.1"
		),
		servers = {
				@Server(url = "http://example.com", description = "本番環境"),
				@Server(url = "http://staging.example.com", description = "ステージング環境"),
				@Server(url = "http://develop.example.com", description = "開発環境")
				/*
				@Server(
						url = "https://{username}.example.com:{port}/{basePath}",
						description = "本番環境",
						variables = {
								@ServerVariable(
										name = "username",
										defaultValue = "demo",
										description = "ユーザー名"
								),
								@ServerVariable(
										name = "port",
										allowableValues = { "8443", "443" },
										defaultValue = "8443"
								),
								@ServerVariable(
										name = "basePath",
										defaultValue = "v2"
								)
						}
				)
				 */
		},
		security = @SecurityRequirement(name = "api_key")
)
public class SpringDocExplanationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDocExplanationApplication.class, args);
	}

}
