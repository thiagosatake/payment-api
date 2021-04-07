package io.payment.api.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "PAYMENTS")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name = "GATEWAY_FK")
	private GatewayEntity gateway;

	@Column(name = "ORDER_NUMBER")
	private String orderNumber;
	
	@Column(name = "ORDER_DESCRIPTION")
	private String orderDescription;
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@Column(name = "CURRENCY")
	private String currency;
	
	@Column(name = "PAYMENT_TYPE")
	private String paymentType;
	
	@Column(name = "CHECKOUT_ID")
	private String checkoutId;
	
	@Column(name = "CHECKOUT_TIME")
	private LocalDateTime checkoutDate;	
	
	@Column(name = "PAYMENT_ID")
	private String paymentId;
	
	@Column(name = "PAYMENT_TIME")
	private LocalDateTime paymentDate;
	
	@Column(name = "PAYMENT_STATUS_CODE")
	private String paymentStatusCode;
	
	@Column(name = "PAYMENT_STATUS_DESCRIPTION")
	private String paymentStatusDescription;
	
	@Column(name = "PAYMENT_USER_ID")
	private UUID paymentUserId;
	
}
