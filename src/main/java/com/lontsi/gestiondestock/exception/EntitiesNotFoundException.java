package com.lontsi.gestiondestock.exception;

import lombok.Getter;

@SuppressWarnings("serial")
public class EntitiesNotFoundException extends RuntimeException {

	@Getter
	private ErrorsCodes errorCode;

	public EntitiesNotFoundException(String message) {
		super(message);
	}

	public EntitiesNotFoundException(String message, ErrorsCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public EntitiesNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntitiesNotFoundException(String message, Throwable cause, ErrorsCodes errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

}
