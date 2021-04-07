package io.payment.api.services.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.payment.api.client.sibs.SibsClient;
import io.payment.api.dto.Checkout;
import io.payment.api.dto.CheckoutPayment;
import io.payment.api.dto.Payment;
import io.payment.api.dto.sibs.CheckoutSibs;
import io.payment.api.dto.sibs.PaymentStatusSibs;
import io.payment.api.dto.sibs.PrepareCheckoutSibs;
import io.payment.api.entities.ConfigurationEntity;
import io.payment.api.entities.GatewayEntity;
import io.payment.api.entities.PaymentEntity;
import io.payment.api.exception.CheckoutException;
import io.payment.api.repositories.ConfigurationRepository;
import io.payment.api.repositories.GatewayConfigurationRepository;
import io.payment.api.repositories.GatewayRepository;
import io.payment.api.repositories.PaymentRepository;
import io.payment.api.services.CheckoutSibsService;
import io.payment.api.services.TokenService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CheckoutSibsServiceImpl implements CheckoutSibsService {

	private static String CHECKOUT_BASE_URL = "checkout_base_url";

	private static String SIBS = "SIBS";
	private static String SIBIS_BASE_URL = "base_url";
	private static String ENTITY_ID = "entity_id";
	private static String TOKEN = "token";

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private GatewayConfigurationRepository gatewayConfigurationRepository;
	
	@Autowired
	private GatewayRepository gatewayRepository;

	@Override
	public CheckoutPayment checkout(Checkout checkout) throws CheckoutException {

		CheckoutSibs sibs = new CheckoutSibs();
		sibs.setAmount(checkout.getAmount());
		sibs.setCurrency(checkout.getCurrency());
		sibs.setPaymentType(checkout.getPaymentType());

		log.info("Preparing Checkout ...");

		String baseUrl = this.gatewayConfigurationRepository.search(SIBIS_BASE_URL, SIBS);
		String entityId = this.gatewayConfigurationRepository.search(ENTITY_ID, SIBS);
		String token = this.gatewayConfigurationRepository.search(TOKEN, SIBS);

		SibsClient sibsClientBuild = SibsClient.builder().baseUrl(baseUrl).entityId(entityId).token(token)
				.restTemplate(this.restTemplate).build();

		PrepareCheckoutSibs prepareCheckout = sibsClientBuild.prepareCheckout(sibs);

		this.setPaymentEntity(checkout, prepareCheckout);

		CheckoutPayment checkoutPayment = new CheckoutPayment();
		checkoutPayment.setPaymentLink(this.generateLink(checkout, prepareCheckout.getId()));

		log.info("Preparing Checkout ... Done.");

		return checkoutPayment;
	}

	@Override
	public Payment payment(String checkoutId, String userId) throws CheckoutException {

		log.info("Creating payment ...");

		String baseUrl = this.gatewayConfigurationRepository.search(SIBIS_BASE_URL, SIBS);
		String entityId = this.gatewayConfigurationRepository.search(ENTITY_ID, SIBS);
		String token = this.gatewayConfigurationRepository.search(TOKEN, SIBS);

		SibsClient sibsClientBuild = SibsClient.builder().baseUrl(baseUrl).entityId(entityId).token(token)
				.restTemplate(this.restTemplate).build();

		PaymentStatusSibs payment = sibsClientBuild.paymentStatus(checkoutId);

		this.setPaymentEntity(payment, checkoutId, userId);

		log.info("Creating payment ... Done.");

		Payment myPayment = new Payment();
		myPayment.setStatus(payment.getResult().getDescription());

		return myPayment;
	}

	private String generateLink(Checkout checkout, String checkoutId) {

		ConfigurationEntity configurationEntity = this.configurationRepository.getOne(CHECKOUT_BASE_URL);

		String token = this.tokenService.generateToken(checkout.getAmount(), checkout.getCurrency(),
				checkout.getPaymentType(), null, checkoutId, checkout.getDescription());

		String baseUrl = configurationEntity.getValue();

		return baseUrl + "/" + token;

	}

	private void setPaymentEntity(PaymentStatusSibs payment, String checkoutId, String userId) {

		PaymentEntity myEntity = this.paymentRepository.findByCheckoutId(checkoutId);
		
		myEntity.setPaymentUserId(UUID.fromString(userId));
		myEntity.setPaymentDate(LocalDateTime.now());
		myEntity.setPaymentId(payment.getId());

		myEntity.setPaymentStatusCode(payment.getResult().getCode());
		myEntity.setPaymentStatusDescription(payment.getResult().getDescription());

		this.paymentRepository.save(myEntity);
	}

	private PaymentEntity setPaymentEntity(Checkout checkout, PrepareCheckoutSibs prepareCheckout) {

		PaymentEntity myEntity = new PaymentEntity();
		
		GatewayEntity gatewayEntity = gatewayRepository.findByName(SIBS);
		
		myEntity.setGateway(gatewayEntity);
		
		myEntity.setOrderNumber(checkout.getOrder());
		myEntity.setOrderDescription(checkout.getDescription());
		myEntity.setAmount(checkout.getAmount());
		myEntity.setCurrency(checkout.getCurrency());
		myEntity.setPaymentType(checkout.getPaymentType());

		myEntity.setCheckoutId(prepareCheckout.getId());
		myEntity.setCheckoutDate(LocalDateTime.now());

		this.paymentRepository.save(myEntity);

		return myEntity;
	}

}
