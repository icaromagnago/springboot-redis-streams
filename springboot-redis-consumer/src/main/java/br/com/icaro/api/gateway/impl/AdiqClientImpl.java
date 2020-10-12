package br.com.icaro.api.gateway.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.icaro.api.dto.auth.Token;
import br.com.icaro.api.gateway.dto.Payment;
import br.com.icaro.api.gateway.dto.PaymentAuthorization;
import br.com.icaro.api.gateway.dto.PaymentResponse;

@Service
public class AdiqClientImpl {
	
	private static final String CLIENT_CREDENTIALS = "client_credentials";

	private static final String GRANT_TYPE = "grant_type";

	@Value("${adiq.url}")
	private String adiqUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public PaymentAuthorization requestPayment(Payment payment, String clientId, String clientSecret) {
		var token = getToken(clientId, clientSecret);
		
		var headers = new HttpHeaders();
		headers.setBearerAuth(token);
		
		var response = restTemplate.postForEntity(adiqUrl+"/v1/payments", 
				new HttpEntity<>(headers), 
				PaymentResponse.class);
		
		return response.getBody().getPaymentAuthorization();
	}
	
	private String getToken(String clientId, String clientSecret) {
		var request = createAuthRequest(clientId, clientSecret);
		
		var response = restTemplate.postForEntity(adiqUrl+"/auth/oauth2/v1/token",
				request, 
				Token.class);
		
		return response.getBody().getAcessToken();
	}

	private HttpEntity<MultiValueMap<String, String>> createAuthRequest(String clientId, String clientSecret) {
		var headers = createAuthHeaders(clientId, clientSecret);
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add(GRANT_TYPE, CLIENT_CREDENTIALS);
		
	    var request = new HttpEntity<>(body, headers);
	    
		return request;
	}

	private HttpHeaders createAuthHeaders(String clientId, String clientSecret) {
		var headers = new HttpHeaders();
		headers.setBasicAuth(clientId, clientSecret);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		return headers;
	}
	
}
