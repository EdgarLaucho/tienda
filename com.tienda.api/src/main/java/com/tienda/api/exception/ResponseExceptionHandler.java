package com.tienda.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tienda.api.response.Response;

@ControllerAdvice
public class ResponseExceptionHandler {
	
	@ExceptionHandler(value = Throwable.class)
	protected ResponseEntity<Response> errorDesconocido(Throwable exception) {

		Response response = new Response(exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
