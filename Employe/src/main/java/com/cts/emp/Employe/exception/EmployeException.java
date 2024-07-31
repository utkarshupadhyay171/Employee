package com.cts.emp.Employe.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
@Data
public class EmployeException {
	private final String message;
	private final Throwable throwable;
	private final HttpStatus httpStatus;

}
