package io.payment.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.payment.api.entities.ConfigurationEntity;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, String> {

}
