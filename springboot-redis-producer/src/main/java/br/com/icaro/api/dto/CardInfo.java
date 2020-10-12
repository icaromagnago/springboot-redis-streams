package br.com.icaro.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardInfo {
	
	@NotBlank(message = "cardHolderName must not be blank or null")
	private String cardHolderName;
}
