package br.com.icaro.api.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import br.com.icaro.api.dto.CancelPayment;
import br.com.icaro.api.dto.PaymentInput;

@Configuration
public class RedisStreamConfiguration {
	
	@Value("${request.payment.stream.key}")
	private String requestPaymentStreamKey;
	
	@Value("${cancel.payment.stream.key}")
	private String cancelPaymentStreamKey;
	
	@Autowired
	private StreamListener<String, ObjectRecord<String, PaymentInput>> requestPaymentListener;
	
	@Autowired
	private StreamListener<String, ObjectRecord<String, CancelPayment>> cancelPaymentListener;
	
	@Bean
	public Subscription requestPaymentSubscription(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
		
		var options = StreamMessageListenerContainer
				.StreamMessageListenerContainerOptions
				.builder()
				.pollTimeout(Duration.ofSeconds(1))
				.targetType(PaymentInput.class)
				.build();
		
		var listenerContainer = StreamMessageListenerContainer
				.create(redisConnectionFactory, options);
		
		var subscription = listenerContainer.receiveAutoAck(
				Consumer.from(requestPaymentStreamKey, InetAddress.getLocalHost().getHostName()), 
				StreamOffset.create(requestPaymentStreamKey, ReadOffset.lastConsumed()), 
				requestPaymentListener);
		
		listenerContainer.start();
		
		return subscription;
	}
	
	@Bean
	public Subscription cancelPaymentSubscription(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
		
		var options = StreamMessageListenerContainer
				.StreamMessageListenerContainerOptions
				.builder()
				.pollTimeout(Duration.ofSeconds(1))
				.targetType(CancelPayment.class)
				.build();
		
		var listenerContainer = StreamMessageListenerContainer
				.create(redisConnectionFactory, options);
		
		var subscription = listenerContainer.receiveAutoAck(
				Consumer.from(cancelPaymentStreamKey, InetAddress.getLocalHost().getHostName()), 
				StreamOffset.create(cancelPaymentStreamKey, ReadOffset.lastConsumed()), 
				cancelPaymentListener);
		
		listenerContainer.start();
		
		return subscription;
	}
	
}
