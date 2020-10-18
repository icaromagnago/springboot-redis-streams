package br.com.icaro.api.delivery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

import br.com.icaro.api.dto.CancelPayment;
import br.com.icaro.api.gateway.AdiqClient;
import br.com.icaro.api.gateway.dto.CancelPaymentRequest;
import br.com.icaro.api.repository.CancelAuthorizationRepository;

@Service
public class CancelPaymentConsumer implements StreamListener<String, ObjectRecord<String, CancelPayment>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CancelPaymentConsumer.class);
	
	@Autowired
	@Qualifier("inMemoryClient")
	private AdiqClient adiqClient;
	
	@Autowired
	private CancelAuthorizationRepository cancelAuthorizationRepo;
	
	@Override
	public void onMessage(ObjectRecord<String, CancelPayment> record) {
		var cancelPaymentRecord = record.getValue();
		
		LOGGER.info("Mensagem consumida: "+ cancelPaymentRecord);
		
		var cancelPayment = new CancelPaymentRequest(cancelPaymentRecord.getAmount());
		
		var cancelAuthorization = adiqClient.cancelPayment(cancelPayment, cancelPaymentRecord.getPaymentId(), 
				cancelPaymentRecord.getClientId(), 
				cancelPaymentRecord.getClientSecret());
		
		cancelAuthorizationRepo.publishEvent(cancelAuthorization);
	}
}
