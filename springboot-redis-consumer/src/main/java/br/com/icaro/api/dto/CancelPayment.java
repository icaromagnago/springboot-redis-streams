package br.com.icaro.api.dto;

import br.com.icaro.api.dto.auth.Credential;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CancelPayment {
	
	private Integer amount;
	
	private Credential credential;
}
