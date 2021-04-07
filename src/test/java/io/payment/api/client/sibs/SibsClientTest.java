package io.payment.api.client.sibs;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import io.payment.api.dto.sibs.CheckoutSibs;
import io.payment.api.dto.sibs.PaymentStatusSibs;
import io.payment.api.dto.sibs.PrepareCheckoutSibs;

@SpringBootTest
public class SibsClientTest {

	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void prepareCheckout() {
		CheckoutSibs checkout = new CheckoutSibs();
		checkout.setAmount(Double.valueOf("92.01"));
		checkout.setCurrency("EUR");
		checkout.setPaymentType("DB");
		
		SibsClient sibsClientBuild = SibsClient.builder()
				.restTemplate(this.restTemplate).build();
		
		PrepareCheckoutSibs prepareCheckout = sibsClientBuild.prepareCheckout(checkout);
		
		assertNotNull(prepareCheckout);
	}
	
	@Test
	public void checkoutPeyment() {
		CheckoutSibs checkout = new CheckoutSibs();
		checkout.setAmount(Double.valueOf("1000.01"));
		checkout.setCurrency("EUR");
		checkout.setPaymentType("DB");
		
		SibsClient sibsClientBuild = SibsClient.builder()
				.restTemplate(this.restTemplate).build();
		
		PrepareCheckoutSibs prepareCheckout = sibsClientBuild.prepareCheckout(checkout);
		
		PaymentStatusSibs payment = sibsClientBuild.paymentStatus(prepareCheckout.getId());
		
		assertNotNull(payment);
	}
	
}
