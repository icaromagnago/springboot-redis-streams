package br.com.icaro.api.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.icaro.api.dto.auth.Credential;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CancelPayment {
	
	private Integer amount;
	
	@NotNull
	@Valid
	private Credential credential;
}
