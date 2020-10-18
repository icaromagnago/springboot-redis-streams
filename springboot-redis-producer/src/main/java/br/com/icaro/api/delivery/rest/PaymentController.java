package br.com.icaro.api.delivery.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.icaro.api.dto.CancelPayment;
import br.com.icaro.api.dto.PaymentInput;
import br.com.icaro.api.repository.CancelPaymentRepository;
import br.com.icaro.api.repository.RequestPaymentRepository;
import br.com.icaro.api.repository.entity.CancelPaymentEntity;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	private RequestPaymentRepository requestPaymentRepository;
	
	@Autowired
	private CancelPaymentRepository cancelPaymentRepository;
	
	@PostMapping
	public ResponseEntity<String> requestPayment(@RequestBody @Valid PaymentInput paymentInput) {
		requestPaymentRepository.publishEvent(paymentInput);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{paymentId}/cancel")
	public ResponseEntity<String> cancelPayment(@PathVariable String paymentId, 
			@RequestBody CancelPayment cancelPayment) {
		
		var cancelPaymentEntity = new CancelPaymentEntity(paymentId, cancelPayment.getAmount(), 
				cancelPayment.getCredential().getClientId(), 
				cancelPayment.getCredential().getClientSecret());
		
		cancelPaymentRepository.publishEvent(cancelPaymentEntity);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/cancel")
	public ResponseEntity<String> cancelPayment2(@RequestParam String paymentId, 
			@RequestBody CancelPayment cancelPayment) {
		
		var cancelPaymentEntity = new CancelPaymentEntity(paymentId, cancelPayment.getAmount(), 
				cancelPayment.getCredential().getClientId(), 
				cancelPayment.getCredential().getClientSecret());
		
		cancelPaymentRepository.publishEvent(cancelPaymentEntity);
		
		return ResponseEntity.ok().build();
	}
}
