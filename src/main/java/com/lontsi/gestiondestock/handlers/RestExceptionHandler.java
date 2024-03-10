package com.lontsi.gestiondestock.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lontsi.gestiondestock.exception.EntitiesNotFoundException;
import com.lontsi.gestiondestock.exception.InvalidEntityException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	 
	@ExceptionHandler(EntitiesNotFoundException.class)
	public ResponseEntity<ErrorsDto> handlerException(EntitiesNotFoundException exception, WebRequest webrequest) {
		final HttpStatus notfound = HttpStatus.NOT_FOUND;

		final ErrorsDto errorDto = ErrorsDto.builder().code(exception.getErrorCode()).httpcode(notfound.value())
				.message(exception.getMessage()).build();
		return new ResponseEntity<>(errorDto, notfound);

	}

	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorsDto> handlerException(InvalidEntityException exception, WebRequest webrequest) {

		final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

		final ErrorsDto errorDto = ErrorsDto.builder().code(exception.getErrorCode()).httpcode(badRequest.value())
				.message(exception.getMessage()).errors(exception.getErrors()).build();

		return new ResponseEntity<>(errorDto, badRequest);

	}
}
