package br.com.icaro.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

import br.com.icaro.api.gateway.dto.PaymentAuthorization;

@Service
public class PaymentAuthorizationRepository {
	
	@Value("${payment.authorization.stream.key}")
	private String paymentAuthorizationStreamKey;
	
	@Autowired
	private ReactiveRedisTemplate<String, String> redisTemplate;
	
	public void publishEvent(PaymentAuthorization paymentAuthorization) {
		var paymentAuthorizationRecord = StreamRecords.newRecord()
				.ofObject(paymentAuthorization)
				.withStreamKey(paymentAuthorizationStreamKey);
		
		redisTemplate.opsForStream()
			.add(paymentAuthorizationRecord)
			.subscribe(System.out::println);
	}
}
