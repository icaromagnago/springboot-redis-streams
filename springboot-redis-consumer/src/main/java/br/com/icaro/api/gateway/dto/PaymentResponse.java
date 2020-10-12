package br.com.icaro.api.gateway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentResponse {
	private PaymentAuthorization paymentAuthorization;
}
