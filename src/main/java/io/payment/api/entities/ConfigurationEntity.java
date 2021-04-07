package io.payment.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CONFIGURATIONS")
public class ConfigurationEntity {

	@Id
	@Column(name = "KEY")
	private String key;
	
	@Column(name = "VALUE")
	private String value;
	
}
