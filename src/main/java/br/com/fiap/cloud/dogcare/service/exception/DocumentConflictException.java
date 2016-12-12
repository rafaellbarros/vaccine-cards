package br.com.fiap.cloud.dogcare.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DocumentConflictException extends APIException {

	
	public DocumentConflictException() {
		super("Documento jã utilizado anteriormente");
	}
	
	
	public DocumentConflictException(String message) {
		super(message);		
	}
	
	
	private static final long serialVersionUID = 1L;

}
