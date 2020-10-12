package br.com.icaro.api.dto.auth;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Credential {
	
	@NotEmpty(message = "clientId must not be null nor empty")
	private String clientId;
	
	@NotEmpty(message = "clientSecret must not be null nor empty")
	private String clientSecret;

}
