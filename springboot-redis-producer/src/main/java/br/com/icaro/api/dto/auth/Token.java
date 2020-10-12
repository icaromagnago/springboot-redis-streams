package br.com.icaro.api.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Token {
	
	private String acessToken;
	
	private String tokenType;
	
	private String expiresIn;
	
	private String scope;
}
