package br.com.icaro.api.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Credential {
	
	private String clientId;
	
	private String clientSecret;

}
