package br.com.icaro.api.delivery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

import br.com.icaro.api.dto.CancelPayment;

@Service
public class CancelPaymentConsumer implements StreamListener<String, ObjectRecord<String, CancelPayment>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CancelPaymentConsumer.class);
	
	@Override
	public void onMessage(ObjectRecord<String, CancelPayment> record) {
		var cancelPaymentRecord = record.getValue();
		
		LOGGER.info("Mensagem consumida: "+ cancelPaymentRecord);
		
	}
}
