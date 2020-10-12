package br.com.icaro.api.gateway.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.icaro.api.gateway.AdiqClient;
import br.com.icaro.api.gateway.dto.Payment;
import br.com.icaro.api.gateway.dto.PaymentAuthorization;

@Service
@Qualifier("inMemoryClient")
public class InMemoryClient implements AdiqClient {

	@Override
	public PaymentAuthorization requestPayment(Payment payment, String clientId, String clientSecret) {
		
		return PaymentAuthorization.builder()
			.returnCode("00")
			.description("Authorized")
			.paymentId("020080286103040952150000006201850000000000")
			.authorizationCode("043711")
			.orderNumber("#12345")
			.expireAt(LocalDateTime.now().plusHours(5))
			.amount(1035)
			.releaseAt(LocalDateTime.now())
			.build();
	}

}
