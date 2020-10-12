package br.com.icaro.api.dto;

public enum TransactionType {
	CREDIT,
	DEBIT;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
