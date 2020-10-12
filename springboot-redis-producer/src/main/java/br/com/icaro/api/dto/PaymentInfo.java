package br.com.icaro.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentInfo {
	
	@NotNull(message = "transactionType must not be null")
	private TransactionType transactionType;
	
	@Positive(message = "amount must be greater than zero")
	private Integer amount;
	
	@NotNull(message = "currencyCode must not be null")
	private CurrencyCode currencyCode;
	
	@NotNull(message = "productType must not be null")
	private ProductType productType;
	
	@PositiveOrZero(message = "installments must be zero or greater than zero")
	private Integer installments;
	
	@NotNull(message = "captureType must not be null")
	private CaptureType captureType;
	
	@NotNull(message = "recurrent must not be null")
	private Boolean recurrent;
}
