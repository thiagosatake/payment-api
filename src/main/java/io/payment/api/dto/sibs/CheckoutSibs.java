package io.payment.api.dto.sibs;

import lombok.Data;

@Data
public class CheckoutSibs {
	
	private Double amount;
	private String currency;
	private String paymentType;
	
}
