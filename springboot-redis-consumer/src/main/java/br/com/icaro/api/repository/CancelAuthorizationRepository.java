package br.com.icaro.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

import br.com.icaro.api.gateway.dto.CancelAuthorization;

@Service
public class CancelAuthorizationRepository {
	
	@Value("${cancel.authorization.stream.key}")
	private String cancelAuthorizationStreamKey;
	
	@Autowired
	private ReactiveRedisTemplate<String, String> redisTemplate;
	
	public void publishEvent(CancelAuthorization cancelAuthorization) {
		var cancelAuthorizationRecord = StreamRecords.newRecord()
				.ofObject(cancelAuthorization)
				.withStreamKey(cancelAuthorizationStreamKey);
		
		redisTemplate.opsForStream()
			.add(cancelAuthorizationRecord)
			.subscribe(System.out::println);
	}
}
