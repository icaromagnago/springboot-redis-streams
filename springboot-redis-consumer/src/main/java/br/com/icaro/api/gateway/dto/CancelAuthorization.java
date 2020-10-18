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
public class CancelAuthorization {
	
	private String returnCode;
	
	private String description;
	
	private String paymentId;
	
	private String authorizationCode;
	
	private Integer amount;
	
	private LocalDateTime releaseAt;
}
