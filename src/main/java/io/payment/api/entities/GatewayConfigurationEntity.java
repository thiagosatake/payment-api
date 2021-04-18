package io.payment.api.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Id
	@Column(name="UUID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uuid;
	
	@ManyToOne
	@JoinColumn(name = "GATEWAY_FK")
	private GatewayEntity gatewayEntity;
	
	@Column(name = "KEY")
	private String key;
	
	@Column(name = "VALUE")
	private String value;

}
