package br.com.icaro.api.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
	
	private PaymentInfo payment;
	
	private CardInfo cardInfo;
	
	private SellerInfo sellerInfo;
	
	@Data
	@Builder
	public static class PaymentInfo {
		private final String transactionType;
		
		private final Integer amount;
		
		private final String currencyCode;
		
		private final String productType;
		
		private final Integer installments;
		
		private final String captureType;
		
		private final Boolean recurrent;
	}
	
	@Data
	public static class SellerInfo {
		private final String orderNumber;
	}
	
	@Data
	public static class CardInfo {
		private final String cardHolderName;
	}
}
