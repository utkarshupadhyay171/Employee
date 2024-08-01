package com.cts.emp.Employe.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeExceptionHandler {
	@ExceptionHandler(value= {EmployeNotFoundException.class})
	public ResponseEntity<Object> handleEmployeNotFoundException
	(EmployeNotFoundException employeNotFoundException){
		EmployeException employeException=new EmployeException(
				employeNotFoundException.getMessage(),
				employeNotFoundException.getCause(),
				HttpStatus.NOT_FOUND
				);
		return new ResponseEntity<>(employeException,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>>handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		Map<String,String> resp=new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(error->{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			resp.put(fieldName,message);
		});
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
		Map<String,String> resp=new HashMap<>();
		String message="Please give some value as input or check the syntax";
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
}
