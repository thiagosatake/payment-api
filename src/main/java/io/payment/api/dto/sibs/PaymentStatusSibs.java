package io.payment.api.dto.sibs;

import lombok.Data;

@Data
public class PaymentStatusSibs {

	private String id;
	private String paymentType;
	private String paymentBrand;
	
	private CardSibs card;
	
	private ResultSibs result;

	private String buildNumber;
	private String timestamp;
	private String ndc;

}