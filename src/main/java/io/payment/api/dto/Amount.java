package io.payment.api.dto;

import lombok.Data;

@Data
public class Amount {

	private String currencyCode;
	private Double value;
	
}
