package io.payment.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.payment.api.entities.GatewayConfigurationEntity;

@Repository
public interface GatewayConfigurationRepository
		extends JpaRepository<GatewayConfigurationEntity, UUID> {

	@Query(value = "SELECT config.value FROM GatewayConfigurationEntity config WHERE config.gatewayEntity.name = :name AND config.key = :key ")
	String search(@Param("key") String key, @Param("name") String name);

}
