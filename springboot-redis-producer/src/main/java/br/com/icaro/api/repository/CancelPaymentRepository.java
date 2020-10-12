package br.com.icaro.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

import br.com.icaro.api.dto.CancelPayment;

@Service
public class CancelPaymentRepository {
	
	@Value("${cancel.payment.stream.key}")
	private String cancelPaymentStreamKey;
	
	@Autowired
	private ReactiveRedisTemplate<String, String> redisTemplate;
	
	public void publishEvent(CancelPayment cancelPayment) {
		var cancelPaymentRecord = StreamRecords.newRecord()
				.ofObject(cancelPayment)
				.withStreamKey(cancelPaymentStreamKey);
		
		redisTemplate.opsForStream()
			.add(cancelPaymentRecord)
			.subscribe(System.out::println);
	}
}
