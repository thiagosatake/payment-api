package io.payment.api.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "GATEWAYS")
@AllArgsConstructor
@NoArgsConstructor
public class GatewayEntity {

	@Id
	@Column(name="UUID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uuid;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATED")
	private LocalDateTime created;

	@Column(name = "UPDATED")
	private LocalDateTime updated;
	
	@OneToMany(mappedBy="gatewayEntity")
	@OrderBy("created DESC")
	private List<GatewayConfigurationEntity> configurations;

}
