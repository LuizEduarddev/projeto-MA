package br.com.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarrinhoException extends RuntimeException
{
	public CarrinhoException(String ex)
	{ 
		super(ex);
	}
	
	private final static Long serialversion = 1L;
}