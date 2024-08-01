package com.cts.emp.Employe.serviceImpl;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.emp.Employe.Jpa.AddresssRepository;
import com.cts.emp.Employe.Jpa.EmployeeeRepository;
import com.cts.emp.Employe.dto.Addressdto;
import com.cts.emp.Employe.entity.Addresss;
import com.cts.emp.Employe.entity.Employeee;
import com.cts.emp.Employe.entity.Loginrequest;
import com.cts.emp.Employe.exception.EmployeNotFoundException;
import com.cts.emp.Employe.response.EmployeResponse;
import com.cts.emp.Employee.service.EmployeeeService;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class EmployeeeServiceImple implements EmployeeeService {
	
	@Autowired
	EmployeeeRepository employeeRepository;
	
	@Autowired
	AddresssRepository addressRepository;
	
	static Logger logger=(Logger) LogManager.getLogger(EmployeeeServiceImple.class);
	
	public Addresss convertToEntity(Addressdto addressDTO) {
	    Addresss address = new Addresss();
	    address.setCity(addressDTO.getCity());
	    address.setState(addressDTO.getState());
	    
	    
	    System.out.println(address);
	    return address;
	}
	
	public ResponseEntity<List<Employeee>> getAllEmployee(int pageNumber,int pageSize) {
		logger.info("Get All Employee Service Imple class running");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent());
	}
	
// Register employe function
	public ResponseEntity<Employeee>register(Employeee employee,HttpServletResponse response){
		logger.info("Add Employee Service Imple class running");
		employeeRepository.save(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(employee);
	}
//	Update employee function
	@CacheEvict(value="employeedetils",key="#name")
	public ResponseEntity<EmployeResponse> updateEmployee(String name, Employeee employeeDetails) {

		logger.info("Update Employee Service Imple class running");
		if( employeeRepository.findByName(name)==null ) {
			throw new EmployeNotFoundException("Wrong employee or employee not present");
		}
		Employeee employee=employeeRepository.findByName(name);
	        Field[] fields = Employeee.class.getDeclaredFields();
	        for (Field field : fields) {
	            field.setAccessible(true);
	            try {
	                Object value = field.get(employeeDetails);
	                if (value != null) {

	                        field.set(employee, value);
//	                    }
	                }
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        
	        }
	        employeeRepository.save(employee);
	        return new ResponseEntity<>(new EmployeResponse(employee,"User updated"),HttpStatus.OK);
	    
	
	}
	@Override
	public ResponseEntity<List<Employeee>> employeeWithSalaryMoreThan1Lakh() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRepository.findEmployeeWithHighSalary());
	}
	
	@Override
	@Cacheable(value="employeedetils",key="#username")
	@CacheEvict(value="employeedetils",key="#username")
	public ResponseEntity<EmployeResponse> getEmployee(String username) {
		logger.info("Find Employee Service Imple class running");
		if( employeeRepository.findByName(username)==null ) {
			throw new EmployeNotFoundException("User name incorrect or user not logged in");
		}
		Employeee emp=employeeRepository.findByName(username);
		return new ResponseEntity<>(new EmployeResponse(emp,"User Found"),HttpStatus.OK);
			}
	
//	Delete employee
	@Override
	@CacheEvict(value="employeedetils",key="#username")
	public ResponseEntity<String> deleteEmployee(String username) {
		
		logger.info("Delete Employee Service Imple class running");
		if( employeeRepository.findByName(username)==null ) {
			throw new EmployeNotFoundException("Wrong employee or employee not present");
		}
		Employeee emp=employeeRepository.findByName(username);
		
			employeeRepository.delete(emp);
			return ResponseEntity.ok("Deleted");
		
	}
//	Update employee address
	@Override
	@CacheEvict(value="employeedetils",key="#username")
	public ResponseEntity<EmployeResponse> updateEmployeeAddress(Integer id,Addresss address,String username) {
		logger.info("Update Employee address Service Imple class running");
		if(employeeRepository.findByName(username)==null) {
			throw new EmployeNotFoundException("User name incorrect or user not logged in");
		}
		Employeee e = employeeRepository.findByName(username);
		Optional<Addresss> addressInOptional=addressRepository.findById(id);
		if( !addressInOptional.isPresent() ) {
			
			throw new EmployeNotFoundException("Either address not added or wrong address id given");
		}
		
		
		Addresss add=addressInOptional.get();
		
		add.setCity(address.getCity());
		add.setState(address.getState());
		addressRepository.save(add);
		return new ResponseEntity<>(new EmployeResponse(e,"User address updated"),HttpStatus.OK);
	}
	
//	Delete current address function
	@Override
	@CacheEvict(value="employeedetils",key="#username")
	public ResponseEntity<String> deleteEmployeeCurrentAddress(String username,Integer id) {
		logger.info("Delete Employee address Service Imple class running");
		Employeee emp=employeeRepository.findByName(username);
		if(emp==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not logged in");
		}
		Optional<Addresss> addOptionalType=addressRepository.findById(id);
		if(!addOptionalType.isPresent()) {
			throw new EmployeNotFoundException("Either address already deleted or wrong address id given");
		}
		
		Addresss add=addOptionalType.get();
		if(id!=emp.currentAddress.getId()) {
			throw new EmployeNotFoundException("Wrong Id given"); 
		}
//		if(add==null)
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found or already deleted");
//		emp.getAddresses().remove(add);
		emp.setCurrentAddress(null);
		addressRepository.delete(add);
		
		employeeRepository.save(emp);
		return ResponseEntity.ok("Address deleted");
	}
//	delete permanent address
	@CacheEvict(value="employeedetils",key="#username")
	public ResponseEntity<String>deleteEmployeePermanentAddress(String username,Integer id){
		logger.info("Delete employee permanent address service impl running");
		Employeee emp=employeeRepository.findByName(username);
		if(emp==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not logged in");
		}
		Optional<Addresss> addressOptionalType=addressRepository.findById(id);
		if(!addressOptionalType.isPresent()) {
			throw new EmployeNotFoundException("Address already deleted");
		}
		Addresss add=addressOptionalType.get();
		if(id!=emp.permanentAddress.getId()) {
			throw new EmployeNotFoundException("Wrong Id given"); 
		}
		
		emp.setPermanentAddress(null);
		addressRepository.delete(add);
		employeeRepository.save(emp);
		return ResponseEntity.ok("Permanent Address Deleted");
	}
	
	
//	Add current address function
	@Override
	@CacheEvict(value="employeedetils",key="#username")
	public ResponseEntity<String> addCurrentEmployeeAddress(Addressdto addressDTO,String username) {
		// TODO Auto-generated method stub
		logger.info("Add Employee current address Service Imple class running");
		System.out.println(addressDTO);
		Addresss address=convertToEntity(addressDTO);
		Employeee emp=employeeRepository.findByName(username);
		if(emp==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not logged in");
		if(emp.getCurrentAddress()!=null)
			throw new EmployeNotFoundException("Current Address already added");
		emp.setCurrentAddress(address);
		address.setEmployee(emp);
		employeeRepository.save(emp);
		return ResponseEntity.ok("Address added");
	}
	
//	Add permsnent address function
	@CacheEvict(value="employeedetils",key="#username")
	public ResponseEntity<String> addPermanentEmployeeAddress(Addressdto addressDTO,String username) {
		logger.info("Add Employee permanent address Service Imple class running");
		System.out.println(addressDTO);
		Addresss address=convertToEntity(addressDTO);
		Employeee emp=employeeRepository.findByName(username);
		
		if(emp==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not logged in");
		if(emp.getPermanentAddress()!=null)
			throw new EmployeNotFoundException("Permanent Address already added");
		emp.setPermanentAddress(address);
		address.setEmployee(emp);
		addressRepository.save(address);
		employeeRepository.save(emp);
		return ResponseEntity.ok("Address added");
	}
	
	@Override
	@CachePut(value="addEmployee",key="#name")
	public ResponseEntity<Addresss> getAddress(String name) {
		return ResponseEntity.ok(addressRepository.findByState(name));
	}

//	Login function
	
	@Override
	public ResponseEntity<String> login(Loginrequest login) {
		if( employeeRepository.findByName(login.getName())==null ){
			throw new EmployeNotFoundException("Employe not registered");
			
		}
		Employeee employee=employeeRepository.findByName(login.getName());
		if(employee.getPassword().equals(login.getPassword()))
		{
			return ResponseEntity.ok("Logged in");
		}
		return ResponseEntity.ok("Password");
	}
	
	
	

}
