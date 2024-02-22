package br.com.server.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.server.exceptions.ExceptionResponse;

@ControllerAdvice
public class CustomizedClienteMesaException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse>
	CustomizedClienteMesaException(Exception ex, WebRequest request)
	{
		ExceptionResponse response = new ExceptionResponse
		(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(response, 
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
