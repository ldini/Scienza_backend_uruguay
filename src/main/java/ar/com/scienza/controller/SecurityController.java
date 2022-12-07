package ar.com.scienza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.CustomServiceException;
import ar.com.scienza.exception.SecurityException;


@ControllerAdvice(basePackages = {"ar.com.scienza"})
public class SecurityController {

	@Autowired
	private Transaction transaction;
	
	
	/**
	 * Interceptor de errores de header
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = SecurityException.class)
    public ResponseEntity<ResponseDto> exception(SecurityException ex) 
	{
		return new ResponseEntity<ResponseDto>(ResponseDto.newError(transaction, ex.getMessage()), HttpStatus.OK);
    }
	
	@ExceptionHandler(value = CustomServiceException.class)
    public ResponseEntity<ResponseDto> exception(CustomServiceException ex) 
	{
		return new ResponseEntity<ResponseDto>(ResponseDto.newCustomError(transaction, ex.getCode(), ex.getMessage()), HttpStatus.OK);
    }
}
