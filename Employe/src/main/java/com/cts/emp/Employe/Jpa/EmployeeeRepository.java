package com.cts.emp.Employe.Jpa;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.emp.Employe.entity.Employeee;


@Repository
public interface EmployeeeRepository extends JpaRepository<Employeee,Integer> {
	
	@Query("SELECT e FROM Employee e WHERE e.salary > 100000")
	List<Employeee>findEmployeeWithHighSalary();
	Employeee findByName(String name);
	

	
}
