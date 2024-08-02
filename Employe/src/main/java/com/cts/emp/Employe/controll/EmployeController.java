package com.cts.emp.Employe.controll;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.emp.Employe.dto.Addressdto;
import com.cts.emp.Employe.dto.Registerdto;
import com.cts.emp.Employe.entity.Addresss;
import com.cts.emp.Employe.entity.Employeee;
import com.cts.emp.Employe.entity.Loginrequest;
import com.cts.emp.Employe.response.EmployeResponse;
import com.cts.emp.Employe.serviceImpl.EmployeeeServiceImple;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;




@RestController
@CrossOrigin("*")
public class EmployeController {
	static Logger logger=(Logger) LogManager.getLogger(EmployeController.class);
	@Autowired
	EmployeeeServiceImple employeeService;
	
	public String help(HttpServletRequest request){
		Cookie[] cookies=request.getCookies();
		String myc=null;
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				System.out.println(cookie.getName());
				if("username".equals(cookie.getName())){
					myc=cookie.getValue();
					break;
				}
			}
		}
		return myc;
		
	}
	
//	API to get details of all employee
	@GetMapping("/getAllEmployee")
	public ResponseEntity<List<Employeee>> getAllEmployee(@RequestParam(defaultValue="0") int pageNumber,@RequestParam(defaultValue="10") int pageSize){
		logger.info("Get All Employee controller running");
		return employeeService.getAllEmployee(pageNumber,pageSize);
	}
	
//	API to get employee with salary more than 1 lath
	@GetMapping("/employeeWithSalaryMoreThan1Lakh")
	public ResponseEntity<List<Employeee>>employeeWithSalaryMoreThan1Lakh(){
		logger.info("Employee with high salary controller running");
		return employeeService.employeeWithSalaryMoreThan1Lakh();
	}
	
//	API to get employee details
	@GetMapping("/getEmployee")
	public ResponseEntity<EmployeResponse>getEmployeeDetails(@RequestParam String username, HttpServletRequest request){
		long startTime=System.nanoTime();
		ResponseEntity<EmployeResponse> result=employeeService.getEmployee(username);
		long endTime=System.nanoTime();
		long duration=(endTime-startTime)/1000000;
		logger.info("Get Employee controller running time {} ms",duration);
		return result;
	}
	
//	API to add employee current address
	@PostMapping("/addCurrentEmployeeAddress")
	public ResponseEntity<String>addEmployeeCurrentpAddress( @RequestParam String username,HttpServletRequest request,@Valid @RequestBody Addressdto addressDTO){
		long startTime=System.nanoTime();
		ResponseEntity<String> result=employeeService.addCurrentEmployeeAddress(addressDTO,username);
		long endTime=System.nanoTime();
		long duration=(endTime-startTime)/1000000;
		logger.info("Add Employee address controller running time {} ms",duration);
		return result;
	}
//	API to add employee permanent address
	@PostMapping("/addPermanentEmployeeAddress")
	public ResponseEntity<String>addEmployeePermanentAddress( @RequestParam String username,HttpServletRequest request,@Valid @RequestBody Addressdto addressDTO){
		long startTime=System.nanoTime();
		ResponseEntity<String> result=employeeService.addPermanentEmployeeAddress(addressDTO,username);
		long endTime=System.nanoTime();
		long duration=(endTime-startTime)/1000000;
		logger.info("Add Employee address controller running time {} ms",duration);
		return result;
	}
	
//	API for employee register
	@PostMapping("/register")
	public ResponseEntity<Employeee>register(@Valid @RequestBody Registerdto employee,HttpServletResponse response){
		long startTime=System.nanoTime();
		ResponseEntity<Employeee> result=employeeService.register(employee,response);
		long endTime=System.nanoTime();
		long duration=(endTime-startTime)/1000000;
		logger.info("Add Employee controller running time {} ms",duration);
		return result;
	}
	
//	API for employee login
	@PostMapping("/login")
	public ResponseEntity<String>login(@Valid @RequestBody Loginrequest login){
		long startTime=System.nanoTime();
		ResponseEntity<String> result=employeeService.login(login);
		long endTime=System.nanoTime();
		long duration=(endTime-startTime)/1000000;
		logger.info("Login employee controller running time {} ms",duration);
		return result;
	}
	
//	API to update Employee details
	@PutMapping("/updateEmployee")
	public ResponseEntity<EmployeResponse>updateEmployeeDetails(HttpServletRequest request,@RequestParam String username,@RequestBody Employeee employee){
		logger.info("Update Employee controller running");
		return employeeService.updateEmployee(username,employee);
	}
	
//	API to update Employee Address
	@PutMapping("/updateEmployeeAddress")
	public ResponseEntity<EmployeResponse>updateEmployeeAddress(@RequestParam String username ,@RequestParam Integer id,@RequestBody Addresss address){
		logger.info("Update Employee address controller running");

		return employeeService.updateEmployeeAddress(id,address,username);
	}
	
//	API to delete Employee
	@DeleteMapping("/deleteEmployee")
	public ResponseEntity<String>deleteEmployee(HttpServletRequest request,@RequestParam String username)
	{
		logger.info("Delete Employee controller running");

		return employeeService.deleteEmployee(username);
	}
	
//	API to delete Employee address
	@DeleteMapping("/deleteEmployeeCurrentAddress")
	public ResponseEntity<String>deleteEmployeeCurrentAddress(HttpServletRequest request,@RequestParam String username,@RequestParam Integer id){
		logger.info("Delete Employee address controller running");
		return employeeService.deleteEmployeeCurrentAddress(username,id);
	}
	
	@DeleteMapping("/deleteEmployeePermanentAddress")
	public ResponseEntity<String>deleteEmployeePermanentAddress(@RequestParam String username,@RequestParam Integer id){
		logger.info("Delete Employee permanent address controller running");
		return employeeService.deleteEmployeePermanentAddress(username,id);
	}
	
	
	@GetMapping("/getAddress")
	public ResponseEntity<Addresss>getAddress(String state){
		return employeeService.getAddress(state);
	}
	

}
