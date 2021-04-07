package io.payment.api.dto;

import lombok.Data;

@Data
public class Checkout {

	private String order;
	private String description;
	
	private Double amount;
	private String currency;
	private String paymentType;
	
}
