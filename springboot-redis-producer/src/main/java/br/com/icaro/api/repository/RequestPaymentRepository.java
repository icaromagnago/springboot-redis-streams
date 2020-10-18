package br.com.icaro.api.repository;

import br.com.icaro.api.dto.PaymentInput;

public interface RequestPaymentRepository {
	
	public void publishEvent(PaymentInput payment);
}
