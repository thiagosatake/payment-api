package io.payment.api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.payment.api.dto.Checkout;
import io.payment.api.dto.CheckoutPayment;
import io.payment.api.dto.Payment;
import io.payment.api.exception.CheckoutException;
import io.payment.api.services.CheckoutSibsService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@OpenAPIDefinition( security = @SecurityRequirement( name = "oauth2-keycloak" ) )
@RequestMapping("/v1/checkout")
public class CheckoutRest extends AbstractSecurityRest {

	@Autowired
	private CheckoutSibsService checkoutSibsService;
	
	@ResponseBody()
	@PostMapping(value = "prepare")
	public ResponseEntity<CheckoutPayment> checkout(@RequestBody Checkout checkout) throws CheckoutException{
		CheckoutPayment prepareCheckout = this.checkoutSibsService.checkout(checkout);
		return new ResponseEntity<CheckoutPayment>(prepareCheckout, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "{checkoutId}/payment")
	public ResponseEntity<Payment> paymentStatus(Principal principal, @PathVariable("checkoutId") String checkoutId ) throws CheckoutException {
		String userId = this.getUserSubject(principal);
		Payment payment = this.checkoutSibsService.payment(checkoutId, userId);

		return new ResponseEntity<Payment>(payment, HttpStatus.OK);
	}
	
}
