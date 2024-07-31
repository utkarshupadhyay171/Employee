package com.cts.emp.Employe.response;

import com.cts.emp.Employe.entity.Employeee;

import lombok.Data;

public class EmployeResponse {
	public Employeee emp;
	public String message;
	public EmployeResponse(Employeee emp, String message) {
		super();
		this.emp = emp;
		this.message = message;  
	}
}
