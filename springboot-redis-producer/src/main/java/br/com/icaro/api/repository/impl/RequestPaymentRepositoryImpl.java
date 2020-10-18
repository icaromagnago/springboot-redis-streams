package br.com.icaro.api.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

import br.com.icaro.api.dto.PaymentInput;
import br.com.icaro.api.repository.RequestPaymentRepository;

@Service
public class RequestPaymentRepositoryImpl implements RequestPaymentRepository {
	
	@Value("${request.payment.stream.key}")
	private String requestPaymentStreamKey;
	
	@Autowired
	private ReactiveRedisTemplate<String, String> redisTemplate;
	
	public void publishEvent(PaymentInput payment) {
		var requestPaymentRecord = StreamRecords.newRecord()
				.ofObject(payment)
				.withStreamKey(requestPaymentStreamKey);
		
		redisTemplate.opsForStream()
			.add(requestPaymentRecord)
			.subscribe(System.out::println);
	}
}
