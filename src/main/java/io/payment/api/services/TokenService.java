package io.payment.api.services;

public interface TokenService {

	String generateToken(Double amount, String currency, String paymentType, String tenantId, String checkoutId, String description);

}
