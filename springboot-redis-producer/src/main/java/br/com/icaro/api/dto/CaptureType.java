package br.com.icaro.api.dto;

public enum CaptureType {
	AC("Autoriza e captura"),
	PA("Pré-Autoriza");
	
	private String description;
	
	private CaptureType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
