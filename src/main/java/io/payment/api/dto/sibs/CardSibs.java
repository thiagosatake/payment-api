package io.payment.api.dto.sibs;

import lombok.Data;

@Data
public class CardSibs {

	private String bin;
	private String last4Digits;
	private String holder;
	private String expiryMonth;
	private String expiryYear;
	
	
}
