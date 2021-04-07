package io.payment.api.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.payment.api.entities.GatewayConfigurationEntity;
import io.payment.api.entities.GatewayConfigurationPK;

@Repository
public interface GatewayConfigurationRepository
		extends CrudRepository<GatewayConfigurationEntity, GatewayConfigurationPK> {

	@Query(value = "SELECT config.value FROM GatewayConfigurationEntity config WHERE config.gatewayConfigurationPK.gatewayEntity.name = :name AND config.gatewayConfigurationPK.key = :key ")
	String search(@Param("key") String key, @Param("name") String name);

}
