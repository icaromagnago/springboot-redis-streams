package br.com.icaro.api.delivery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

import br.com.icaro.api.dto.PaymentInput;
import br.com.icaro.api.gateway.AdiqClient;
import br.com.icaro.api.gateway.dto.Payment;
import br.com.icaro.api.gateway.dto.Payment.PaymentInfo;
import br.com.icaro.api.repository.PaymentAuthorizationRepository;

@Service
public class RequestPaymentConsumer implements StreamListener<String, ObjectRecord<String, PaymentInput>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestPaymentConsumer.class);
	
	@Autowired
	@Qualifier("inMemoryClient")
	private AdiqClient adiqClient;
	
	@Autowired
	private PaymentAuthorizationRepository paymentAuthorizationRepository;
	
	@Override
	public void onMessage(ObjectRecord<String, PaymentInput> record) {
		var paymentRecord = record.getValue();
		
		LOGGER.info("Mensagem consumida: "+ paymentRecord);
		
		PaymentInfo paymentInfo = Payment.PaymentInfo
				.builder()
				.amount(paymentRecord.getPayment().getAmount())
				.captureType(paymentRecord.getPayment().getCaptureType().name().toLowerCase())
				.currencyCode(paymentRecord.getPayment().getCurrencyCode().name().toLowerCase())
				.installments(paymentRecord.getPayment().getInstallments())
				.productType(paymentRecord.getPayment().getProductType().name().toLowerCase())
				.recurrent(paymentRecord.getPayment().getRecurrent())
				.transactionType(paymentRecord.getPayment().getTransactionType().name().toLowerCase())
				.build();
		
		var paymentDto = Payment.builder()
			.payment(paymentInfo)
			.cardInfo(new Payment.CardInfo(paymentRecord.getCardInfo().getCardHolderName()))
			.sellerInfo(new Payment.SellerInfo(paymentRecord.getSellerInfo().getOrderNumber()))
			.build();
		
		var paymentAuthorization = adiqClient.requestPayment(paymentDto, 
				paymentRecord.getCredential().getClientId(), 
				paymentRecord.getCredential().getClientSecret());
		
		paymentAuthorizationRepository.publishEvent(paymentAuthorization);	
	}
}
