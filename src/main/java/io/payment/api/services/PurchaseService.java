package io.payment.api.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.payment.api.dto.PurchaseList;

@Service
public interface PurchaseService {

	public PurchaseList getHistory(UUID userId);
	
}
