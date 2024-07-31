package com.cts.emp.Employe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity(name="Address")
@Data
public class Addresss {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String city;
	public String state;
//	@ManyToOne
//	@JsonBackReference
//	public Employeee employee;
//	@Override
//	   public String toString() {
//	       return "Address{" +
//	               "id=" + id +
//	               ", city='" + city + '\'' +
//	               ", state='" + state + '\'' +
//	               ", employeeId=" + (employee != null ? employee.getId() : "null") +
//	               '}';
//	   }
	@ManyToOne
	@JsonBackReference
	public Employeee employee;
	
	
}
