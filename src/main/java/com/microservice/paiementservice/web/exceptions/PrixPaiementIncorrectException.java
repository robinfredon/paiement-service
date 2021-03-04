package com.microservice.paiementservice.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PrixPaiementIncorrectException extends RuntimeException {

	public PrixPaiementIncorrectException(String s) {
		super(s);
	}
}
