package io.payment.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.payment.api.entities.GatewayEntity;

@Repository
public interface GatewayRepository extends JpaRepository<GatewayEntity, UUID> {
	
	GatewayEntity findByName(String name);
	
}
