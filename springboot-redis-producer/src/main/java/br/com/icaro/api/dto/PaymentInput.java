package br.com.icaro.api.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.icaro.api.dto.auth.Credential;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentInput {
	
	@Valid
	@NotNull(message = "payment must not be null")
	private PaymentInfo payment;
	
	@Valid
	@NotNull(message = "cardInfo must not be null")
	private CardInfo cardInfo;
	
	@Valid
	@NotNull(message = "sellerInfo must not be null")
	private SellerInfo sellerInfo;
	
	@Valid
	@NotNull(message = "credential must not be null")
	private Credential credential;
}
