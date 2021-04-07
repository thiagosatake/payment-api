package io.payment.api.services.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.payment.api.services.TokenService;

@Component
public class TokenServiceImpl implements TokenService {

	@Override
	public String generateToken(Double amount, String currency, String paymentType, String tenantId, String checkoutId,
			String description) {
		Instant now = Instant.now();

		String jwtToken = Jwts.builder().claim("checkoutId", checkoutId).claim("description", description)
				.claim("tenantId", tenantId).claim("amount", amount).claim("paymentType", paymentType)
				.claim("currency", currency).setSubject("pay").setId(UUID.randomUUID().toString())
				.setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES))).compact();

		return jwtToken;
	}
	
}
