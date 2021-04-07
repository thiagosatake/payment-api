package io.payment.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "GATEWAY_CONFIGURATIONS")
public class GatewayConfigurationEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GatewayConfigurationPK gatewayConfigurationPK;
	
	@Column(name = "VALUE")
	private String value;

}
