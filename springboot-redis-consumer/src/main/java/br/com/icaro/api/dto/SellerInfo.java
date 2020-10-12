package br.com.icaro.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SellerInfo {
	
	@NotBlank(message = "orderNumber must not be blank or null")
	private String orderNumber;
}
