package io.payment.api.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import io.payment.api.entities.GatewayConfigurationEntity;
import io.payment.api.entities.GatewayEntity;
import io.payment.api.mappers.GatewayMapper;
import io.payment.api.repositories.GatewayConfigurationRepository;
import io.payment.api.repositories.GatewayRepository;
import io.payment.api.services.GatewaysService;

@Component
public class GatewaysServiceImpl implements GatewaysService {

	@Autowired
	private GatewayMapper gatewayMapper;

	@Autowired
	private GatewayRepository gatewayRepository;

	@Autowired
	private GatewayConfigurationRepository gatewayConfigurationRepository;

	@Override
	public Gateway getGatewayById(UUID uuid) {
		Optional<GatewayEntity> gatewayOptional = this.gatewayRepository.findById(uuid);
		Gateway gateway = this.gatewayMapper.toGateway( gatewayOptional.get() );
		return gateway;
	}
	
	@Override
	public Gateway getGatewayByName(String name) {
		GatewayEntity gatewayEntity = this.gatewayRepository.findByName(name);
		Gateway gateway= this.gatewayMapper.toGateway(gatewayEntity);
		return gateway;
	}

	@Override
	public List<GatewayDetails> listGateway() {
		List<GatewayEntity> listGatewayEntity = this.gatewayRepository.findAll(Sort.by(Sort.Direction.DESC, "created"));
		List<GatewayDetails> gatewayDetailsList = this.gatewayMapper.toGatewayDetailsList(listGatewayEntity);
		return gatewayDetailsList;
	}

	@Override
	public void saveGateway(Gateway gateway) {
		GatewayEntity gatewayEntity;
		if( gateway.getUuid() == null ) {
			gatewayEntity = this.gatewayMapper.toGatewayEntity(gateway);
			gatewayEntity.setCreated(LocalDateTime.now());
		}else {
			gatewayEntity = this.gatewayRepository.getOne(gateway.getUuid());	
			gatewayEntity.setName(gateway.getName());
			gatewayEntity.setDescription(gateway.getDescription());
		}
		gatewayEntity.setUpdated(LocalDateTime.now());
		
		this.gatewayRepository.save(gatewayEntity);

		gateway.setUuid(gatewayEntity.getUuid());
	}

	@Override
	public void removeGateway(UUID uuid) {
		GatewayEntity gatewayEntity = this.gatewayRepository.getOne(uuid);		
		List<GatewayConfigurationEntity>  gatewayConfigurationEntity = gatewayEntity.getConfigurations();		
		gatewayConfigurationEntity.forEach( item->this.gatewayConfigurationRepository.delete(item) );
		this.gatewayRepository.delete(gatewayEntity);
	}

	@Override
	public void removeGatewayConfiguration(UUID gatewayConfigurationId) {
		this.gatewayConfigurationRepository.deleteById(gatewayConfigurationId);
	}

	@Override
	public void saveGatewayConfiguration(UUID gatewayId, GatewayConfiguration gatewayConfiguration) {
		GatewayEntity gatewayEntity = this.gatewayRepository.getOne(gatewayId);

		GatewayConfigurationEntity gatewayConfigurationEntity = new GatewayConfigurationEntity();
		
		if( gatewayConfiguration.getUuid() == null ) {
			gatewayConfigurationEntity.setCreated(LocalDateTime.now());
		}else {
			gatewayConfigurationEntity = this.gatewayConfigurationRepository.getOne(gatewayConfiguration.getUuid());
		}
		
		gatewayConfigurationEntity.setKey(gatewayConfiguration.getKey());		
		gatewayConfigurationEntity.setValue(gatewayConfiguration.getValue());
		
		gatewayConfigurationEntity.setGatewayEntity(gatewayEntity);
		
		gatewayConfigurationEntity.setUpdated(LocalDateTime.now());
		
		this.gatewayConfigurationRepository.save(gatewayConfigurationEntity);
		
		gatewayConfiguration.setUuid(gatewayConfigurationEntity.getUuid());
	}

	@Override
	public GatewayDetails getGatewayDetailsById(UUID uuid) {
		GatewayEntity gatewayEntity = this.gatewayRepository.getOne(uuid);
		GatewayDetails gatewayDetails = this.gatewayMapper.toGatewayDetails(gatewayEntity);
		return gatewayDetails;
	}

}
