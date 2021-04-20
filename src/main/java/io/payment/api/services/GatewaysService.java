package io.payment.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
public interface GatewaysService {

	public Gateway getGatewayById(UUID uuid);
	
	public GatewayDetails getGatewayDetailsById(UUID uuid);
	
	public GatewayConfiguration getConfigurationById(UUID uuid);
	
	public Gateway getGatewayByName(String name);
	
	public List<GatewayDetails> listGateway();
	
	public void saveGateway(Gateway gateway);
	
	public void removeGateway(UUID uuid);
	
	public void removeGatewayConfiguration(UUID gatewayConfigurationID);
	
	public void saveGatewayConfiguration(UUID gatewayID, GatewayConfiguration gatewayConfiguration);
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	class Gateway {
		
		private UUID uuid;
		private String name;
		private String description;
			
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	class GatewayDetails {
		
		private UUID uuid;
		private String name;
		private String description;
		
		private List<GatewayConfiguration> configurations;
	
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	class GatewayConfiguration {
		
		private UUID uuid;
		private String key;
		private String value;
			
	}
	
}
