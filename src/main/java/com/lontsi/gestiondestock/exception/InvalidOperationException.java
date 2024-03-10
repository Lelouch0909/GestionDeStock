package com.lontsi.gestiondestock.exception;

import lombok.Getter;

public class InvalidOperationException extends RuntimeException {

	@Getter
	private ErrorsCodes errorCode;

	public InvalidOperationException(String message) {
		super(message);
	}

	public InvalidOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidOperationException(String message, Throwable cause, ErrorsCodes errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public InvalidOperationException(String message, ErrorsCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
