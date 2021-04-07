package io.payment.api.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Purchase {
	
	private String orderNumber;
	private String orderDescription;
	private Double amount;
	private String currency;
	private String paymentType;
	private String paymentStatusCode;
	private String paymentStatusDescription; 
	private LocalDateTime paymentDate;
	
}
