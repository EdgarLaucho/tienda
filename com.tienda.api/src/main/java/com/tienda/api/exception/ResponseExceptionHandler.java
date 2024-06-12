package com.tienda.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tienda.api.entity.Employees;
import com.tienda.api.response.Response;

@ControllerAdvice
public class ResponseExceptionHandler {
	
	@ExceptionHandler(value = Throwable.class)
	protected ResponseEntity<Response> errorDesconocido(Throwable exception) {
		Response response = new Response(exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

	}//fin 
	
	@ExceptionHandler(value = NotFoundException.class)
	protected ResponseEntity<Response> NotFoundException(NotFoundException foundTransactionException) {
		Response response = new Response(foundTransactionException.getMessage());
		 return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}// Fin NotFoundException

	@ExceptionHandler(value = BadRequestException.class)
	protected ResponseEntity<Response> BadRequestException(BadRequestException badRequestException) {
		Response response = new Response(badRequestException.getMessage());
		 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	}// Fin BadRequestException
	
}
