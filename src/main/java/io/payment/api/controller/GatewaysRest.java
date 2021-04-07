package io.payment.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.payment.api.services.GatewaysService;
import io.payment.api.services.GatewaysService.Gateway;
import io.payment.api.services.GatewaysService.GatewayConfiguration;
import io.payment.api.services.GatewaysService.GatewayDetails;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@OpenAPIDefinition( security = @SecurityRequirement( name = "oauth2-keycloak" ) )
@RequestMapping("gateways")
public class GatewaysRest {
	
	@Autowired
	private GatewaysService gatewaysService;
	
	@ResponseBody
	@GetMapping("/v1")
	public ResponseEntity<List<GatewayDetails>> getGateways(){
		List<GatewayDetails> listGatewayDetails = this.gatewaysService.listGateway();
		return new ResponseEntity<List<GatewayDetails>>(listGatewayDetails, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/v1/{uuid}")
	public ResponseEntity<Gateway> getGateways(@PathVariable UUID uuid){
		Gateway gateway = this.gatewaysService.getGatewayById(uuid);
		return new ResponseEntity<Gateway>(gateway, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/v1/name/{name}")
	public ResponseEntity<Gateway> getByName(@PathVariable String name){
		Gateway gateway = this.gatewaysService.getGatewayByName(name);
		return new ResponseEntity<Gateway>(gateway, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping("/v1/{gatewayId}")
	public ResponseEntity<Void> removeGateway(@PathVariable UUID gatewayId){
		this.gatewaysService.removeGateway(gatewayId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ResponseBody
	@PutMapping("/v1/save")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "409", description = "Conflict")		
	})
	public ResponseEntity<UUID> saveGateway(@RequestBody Gateway gateway){
		this.gatewaysService.saveGateway(gateway);
		return new ResponseEntity<UUID>(gateway.getUuid(), HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("/v1/{gatewayId}/configurations/save")
	public ResponseEntity<Void> saveNewGatewayConfiguration(@PathVariable UUID gatewayId, @RequestBody GatewayConfiguration gatewayConfiguration){
		this.gatewaysService.saveGatewayConfiguration(gatewayId, gatewayConfiguration);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping("/v1/{gatewayId}/configurations/{key}/remove")
	public ResponseEntity<Void> saveGatewayConfiguration(@PathVariable UUID gatewayId, @PathVariable String key){
		this.gatewaysService.removeGatewayConfiguration(gatewayId, key);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	

	
}

