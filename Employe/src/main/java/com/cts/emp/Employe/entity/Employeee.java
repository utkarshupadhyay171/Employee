package com.cts.emp.Employe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity(name="Employee")
@Data
public class Employeee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	public Integer id;
	public String name;
	public String password;
	public String age;
	public Integer salary;
	
//	@OneToMany(mappedBy="employee",fetch = FetchType.EAGER
//			,cascade = CascadeType.ALL, 
//			orphanRemoval = true)
//	@JsonManagedReference
//	public List<Addresss>addresses;
//	@Override
//	   public String toString() {
//	       return "Employee{" +
//	               "id=" + id +
//	               ", name='" + name + '\'' +
//	               ", password='" + password + '\'' +
//	               ", age='" + age + '\'' +
//	               ", salary=" + salary +
//	               ", addressesCount=" + (addresses != null ? addresses.size() : "null") +
//	               '}';
//	   }
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)
	@JsonManagedReference
	@JoinColumn(name="current_address_id")
	public Addresss currentAddress;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)
	@JsonManagedReference
	@JoinColumn(name="permanant_address_id")
	public Addresss permanentAddress; 

}
