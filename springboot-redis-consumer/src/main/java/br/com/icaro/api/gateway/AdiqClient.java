package br.com.icaro.api.gateway;

import br.com.icaro.api.gateway.dto.CancelAuthorization;
import br.com.icaro.api.gateway.dto.CancelPaymentRequest;
import br.com.icaro.api.gateway.dto.Payment;
import br.com.icaro.api.gateway.dto.PaymentAuthorization;

public interface AdiqClient {
	
	PaymentAuthorization requestPayment(Payment payment, String clientId, String clientSecret);
	
	CancelAuthorization cancelPayment(CancelPaymentRequest cancelPayment,
			String paymentId, String clientId, String clientSecret);
}
