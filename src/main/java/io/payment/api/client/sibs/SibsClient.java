package io.payment.api.client.sibs;

import java.text.DecimalFormat;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import io.payment.api.dto.sibs.CheckoutSibs;
import io.payment.api.dto.sibs.PaymentStatusSibs;
import io.payment.api.dto.sibs.PrepareCheckoutSibs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
public class SibsClient {
	
	private String baseUrl;
	private String entityId;
	private String token;
	@NotNull
	private RestTemplate restTemplate;
	
	@Builder
	SibsClient(String baseUrl, String entityId, String token, RestTemplate restTemplate ) {
		this.baseUrl = StringUtils.isBlank( baseUrl ) ? "https://test.oppwa.com" : baseUrl;
		this.entityId = StringUtils.isBlank( entityId ) ? "8a8294185332bbe601533754724914d9" : entityId;
		this.token = StringUtils.isBlank( token ) ? "OGE4Mjk0MTg1MzMyYmJlNjAxNTMzNzU0NzZjMzE1Mjd8RzV3UDVUekY1aw==" : token;
		this.restTemplate = restTemplate;
	}
	
	
	public PrepareCheckoutSibs prepareCheckout(CheckoutSibs checkout) {
		
		final String myUrlCheckout = this.baseUrl + "/v1/checkouts";
		
		log.debug("Starting prepare checkout: " + myUrlCheckout );
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(this.token);
	
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("entityId",this.entityId );
		
		map.add("currency", checkout.getCurrency());
		map.add("paymentType", checkout.getPaymentType());
		
		DecimalFormat df = new DecimalFormat("#.00");
		map.add("amount", df.format(checkout.getAmount()));
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		ResponseEntity<PrepareCheckoutSibs> response = restTemplate.postForEntity(myUrlCheckout, request, PrepareCheckoutSibs.class);
		if ( HttpStatus.OK.equals(response.getStatusCode()) ) { 
			log.info("Prepare checkout successfully finished.");
		} else {
			log.error("Prepare checkout fail with status code: " + response.getStatusCodeValue() );
		}
		return response.getBody();
	}
	
	public PaymentStatusSibs paymentStatus(String checkoutId) {
		
		final String myUrlPaymentStatus = this.baseUrl + "/v1/checkouts/" + checkoutId + "/payment?entityId="+this.entityId;
		
		log.debug("Starting prepare checkout: " + myUrlPaymentStatus );
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(this.token);
		
		HttpEntity<?> request = new HttpEntity<Object>(headers);
		
  		ResponseEntity<PaymentStatusSibs> response = restTemplate.exchange(myUrlPaymentStatus, HttpMethod.GET, request, PaymentStatusSibs.class);
		if ( HttpStatus.OK.equals(response.getStatusCode()) ) { 
			log.info("Payment checkout successfully finished.");
		} else {
			log.error("Payment checkout fail with status code: " + response.getStatusCodeValue() );
		}
		return response.getBody();
	}
		
}
