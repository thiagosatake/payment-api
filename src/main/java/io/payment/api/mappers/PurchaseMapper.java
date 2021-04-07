package io.payment.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import io.payment.api.dto.Purchase;
import io.payment.api.entities.PaymentEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PurchaseMapper {

	Purchase toPurchase(PaymentEntity entity);
	
	List<Purchase> toPurchaseList(List<PaymentEntity> entityList);
	
}
