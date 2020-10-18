package br.com.icaro.api.dto;

import lombok.Data;

@Data
public class CancelPayment {
	
	private final String paymentId;
	
	private final Integer amount;
	
	private final String clientId;
	
	private final String clientSecret;
}
