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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity(name="Employee")
@Data
public class Employeee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	public Integer id;
	@NotEmpty(message="Username must not be empty")
	public String name;
	@NotEmpty(message="Password must not be empty")
	public String password;
	@NotEmpty(message="Age must not be 0")
	public String age;
	@NotNull(message="Salary must not be empty")
	@Min(value=1,message="Salary must not be 0 ")
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
