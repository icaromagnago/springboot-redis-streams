package br.com.icaro.api.dto;

public enum CurrencyCode {
	BRL;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
