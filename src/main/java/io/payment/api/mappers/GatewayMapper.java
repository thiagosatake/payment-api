package io.payment.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import io.payment.api.entities.GatewayConfigurationEntity;
import io.payment.api.entities.GatewayEntity;
import io.payment.api.services.GatewaysService.Gateway;
import io.payment.api.services.GatewaysService.GatewayConfiguration;
import io.payment.api.services.GatewaysService.GatewayDetails;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GatewayMapper {

	Gateway toGateway(GatewayEntity entity);
	
	GatewayDetails toGatewayDetails(GatewayEntity entity);
	
	List<GatewayDetails> toGatewayDetailsList(List<GatewayEntity> entityList);

	GatewayEntity toGatewayEntity(GatewayDetails gatewayDetails);
	
	GatewayEntity toGatewayEntity(Gateway gateway);

	List<GatewayEntity> toGatewayEntityList(List<GatewayDetails> gatewayDetailsList);

	GatewayConfigurationEntity toGatewayConfigurationEntity(GatewayConfiguration gatewayConfiguration);
	
	List<GatewayConfigurationEntity> toGatewayConfigurationsList(List<GatewayConfiguration> gatewayConfigurations);
	
	GatewayConfiguration toGatewayConfigurations(GatewayConfigurationEntity gatewayConfigurationEntity);
	
	List<GatewayConfiguration> toGatewayConfigurationEntityList(List<GatewayConfigurationEntity> gatewayConfigurationEntity);

}
