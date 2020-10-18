package br.com.icaro.api.repository;

import br.com.icaro.api.repository.entity.CancelPaymentEntity;

public interface CancelPaymentRepository {
	
	public void publishEvent(CancelPaymentEntity cancelPaymentEntity);
}
