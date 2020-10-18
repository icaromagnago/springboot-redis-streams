package br.com.icaro.api.gateway.dto;

import lombok.Data;

@Data
public class CancelPaymentRequest {
	
	private final Integer amount;
}
