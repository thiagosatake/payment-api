package io.payment.api.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.payment.api.dto.Purchase;
import io.payment.api.dto.PurchaseList;
import io.payment.api.entities.PaymentEntity;
import io.payment.api.mappers.PurchaseMapper;
import io.payment.api.repositories.PaymentRepository;
import io.payment.api.services.PurchaseService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseMapper purchaseMapper;

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public PurchaseList getHistory(UUID userId) {
		log.info("Getting purchase history ...");

		List<PaymentEntity> paymentEnityList = paymentRepository.findByPaymentUserId(userId);
		List<Purchase> purchases = this.purchaseMapper.toPurchaseList(paymentEnityList);

		PurchaseList purchaseList = new PurchaseList();
		purchaseList.setList(purchases);
		purchaseList.setCount(purchases != null ? purchases.size() : 0);
		purchaseList.setUserId(userId);
		log.info("Getting purchase history ... Done.");
		return purchaseList;
	}

}
