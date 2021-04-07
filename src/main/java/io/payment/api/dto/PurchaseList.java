package io.payment.api.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class PurchaseList {
	
	private UUID userId;
	private Integer count;
	private List<Purchase> list;
	
}
