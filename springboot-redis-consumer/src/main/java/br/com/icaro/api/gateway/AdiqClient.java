package br.com.icaro.api.gateway;

import br.com.icaro.api.gateway.dto.Payment;
import br.com.icaro.api.gateway.dto.PaymentAuthorization;

public interface AdiqClient {
	PaymentAuthorization requestPayment(Payment payment, String clientId, String clientSecret);
}
