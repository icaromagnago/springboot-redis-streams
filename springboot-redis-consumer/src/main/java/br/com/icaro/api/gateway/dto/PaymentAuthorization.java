package br.com.icaro.api.gateway.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAuthorization {
	
	private String returnCode;
	
	private String description;
	
	private String paymentId;
	
	private String authorizationCode;
	
	private String orderNumber;
	
	private LocalDateTime expireAt;
	
	private Integer amount;
	
	private LocalDateTime releaseAt;
}
