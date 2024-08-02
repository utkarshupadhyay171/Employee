package com.cts.emp.Employe.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Registerdto {
	@NotEmpty(message="Name can not be empty")
	String name;
	@NotEmpty(message="Password can not be empty")
	String password;
	@NotEmpty(message="Age can not be empty")
	String age;
	@NotNull(message="Salary can not be 0")
	@Min(value =1,message="Salary must be greater 0")
	Integer salary;

}
