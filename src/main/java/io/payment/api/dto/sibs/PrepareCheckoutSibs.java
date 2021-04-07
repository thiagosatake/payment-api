package io.payment.api.dto.sibs;

import lombok.Data;

@Data
public class PrepareCheckoutSibs {

	private ResultSibs result;

	private String buildNumber;
	private String timestamp;
	private String ndc;
	private String id;
	
}
