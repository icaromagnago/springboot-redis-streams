package br.com.icaro.api.dto;

public enum ProductType {
	AVISTA,
	EMISSOR,
	LOGISTA,
	DEBITO;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
