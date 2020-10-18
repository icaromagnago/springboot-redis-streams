package br.com.icaro.api.repository.entity;

import lombok.Data;

@Data
public class CancelPaymentEntity {
	
	private final String paymentId;
	
	private final Integer amount;
	
	private final String clientId;
	
	private final String clientSecret;
}
