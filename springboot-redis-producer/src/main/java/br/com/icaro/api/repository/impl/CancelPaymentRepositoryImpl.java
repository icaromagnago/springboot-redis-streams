package br.com.icaro.api.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

import br.com.icaro.api.repository.CancelPaymentRepository;
import br.com.icaro.api.repository.entity.CancelPaymentEntity;

@Service
public class CancelPaymentRepositoryImpl implements CancelPaymentRepository {
	
	@Value("${cancel.payment.stream.key}")
	private String cancelPaymentStreamKey;
	
	@Autowired
	private ReactiveRedisTemplate<String, String> redisTemplate;
	
	public void publishEvent(CancelPaymentEntity cancelPaymentEntity) {
		var cancelPaymentRecord = StreamRecords.newRecord()
				.ofObject(cancelPaymentEntity)
				.withStreamKey(cancelPaymentStreamKey);
		
		redisTemplate.opsForStream()
			.add(cancelPaymentRecord)
			.subscribe(System.out::println);
	}
}
