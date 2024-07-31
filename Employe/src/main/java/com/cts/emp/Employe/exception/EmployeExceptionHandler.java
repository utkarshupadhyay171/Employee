package com.cts.emp.Employe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
