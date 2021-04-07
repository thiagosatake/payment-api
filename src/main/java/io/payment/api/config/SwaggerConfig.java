package io.payment.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
public class SwaggerConfig {

	@Value("${keycloak.auth-server-url}")
	private String authUrl;

	@Value("${keycloak.realm}")
	private String realm;

	@Autowired
	private BuildProperties buildProperties;

	@Bean
	public OpenAPI springOpenAPI() {
		OpenAPI openAPI = new OpenAPI().info(new Info().title(buildProperties.getName())
				.description("Build time: " + buildProperties.getTime()).version(buildProperties.getVersion()));
		openAPI.schemaRequirement("oauth2-keycloak", this.securityScheme());
		return openAPI;
	}

	private SecurityScheme securityScheme() {
		OAuthFlow clientCredential = new OAuthFlow();
		clientCredential.setTokenUrl(authUrl + "/realms/" + realm + "/protocol/openid-connect/token");

		OAuthFlows oauthFlows = new OAuthFlows();
		//oauthFlows.clientCredentials(clientCredential);
		oauthFlows.password(clientCredential);
		
		SecurityScheme securityScheme = new SecurityScheme();
		securityScheme.setType(Type.OAUTH2);
		securityScheme.setFlows(oauthFlows);

		return securityScheme;
	}

}
