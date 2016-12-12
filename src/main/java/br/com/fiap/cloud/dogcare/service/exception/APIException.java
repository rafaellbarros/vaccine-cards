package br.com.fiap.cloud.dogcare.service.exception;

public class APIException extends Exception {

	public APIException(String message) {
		super(message);		
	}

	private static final long serialVersionUID = 1L;
}
