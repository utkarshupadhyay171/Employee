package com.cts.emp.Employee.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.emp.Employe.dto.Addressdto;
import com.cts.emp.Employe.entity.Addresss;
import com.cts.emp.Employe.entity.Employeee;
import com.cts.emp.Employe.entity.Loginrequest;
import com.cts.emp.Employe.response.EmployeResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface EmployeeeService {
	
	
	
	public ResponseEntity<List<Employeee>> getAllEmployee(int pageNumber,int pageSize); 
	public ResponseEntity<Employeee>register(Employeee employee,HttpServletResponse response);
	public ResponseEntity<EmployeResponse>updateEmployee(String name,Employeee employee);
	public ResponseEntity<List<Employeee>>employeeWithSalaryMoreThan1Lakh();
	public ResponseEntity<EmployeResponse>getEmployee(String username);
	public ResponseEntity<String>deleteEmployee(String username);
	public ResponseEntity<EmployeResponse>updateEmployeeAddress(Integer id,Addresss address,String username);
	public ResponseEntity<String>deleteEmployeeCurrentAddress(String username,Integer id);
	public ResponseEntity<String>deleteEmployeePermanentAddress(String username,Integer id);
	public ResponseEntity<String>addCurrentEmployeeAddress(Addressdto address,String username);
	public ResponseEntity<String>addPermanentEmployeeAddress(Addressdto address,String username);
	public ResponseEntity<Addresss>getAddress(String name);
	public ResponseEntity<String>login(Loginrequest login);

}
