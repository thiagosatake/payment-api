package io.payment.api.controller;

import java.security.Principal;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public class AbstractSecurityRest {

	String getUserSubject(final Principal principal) {
		KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
		String stringUserId = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken()
				.getSubject();
		return stringUserId;
	}
	
}
