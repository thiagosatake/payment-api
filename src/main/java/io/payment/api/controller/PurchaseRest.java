package io.payment.api.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.payment.api.dto.Purchase;
import io.payment.api.dto.PurchaseList;
import io.payment.api.exception.CheckoutException;
import io.payment.api.services.PurchaseService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@OpenAPIDefinition( security = @SecurityRequirement( name = "oauth2-keycloak" ) )
@RequestMapping("purchase")
public class PurchaseRest extends AbstractSecurityRest {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@ResponseBody
	@GetMapping(value = "/v1/history")
	public ResponseEntity<List<Purchase>> paymentStatus(Principal principal) throws CheckoutException {
		String userId = this.getUserSubject(principal);
		PurchaseList purchaseList = this.purchaseService.getHistory(UUID.fromString(userId));
		return new ResponseEntity<List<Purchase>>(purchaseList.getList(), HttpStatus.OK);
	}

}
