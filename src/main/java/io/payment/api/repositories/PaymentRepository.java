package io.payment.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.payment.api.entities.PaymentEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, String>  {

	PaymentEntity findByCheckoutId(String checkoutId);
	
	List<PaymentEntity> findByPaymentUserId(UUID userId);
	
}
