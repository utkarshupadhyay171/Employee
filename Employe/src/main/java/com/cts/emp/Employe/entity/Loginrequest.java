package com.cts.emp.Employe.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Loginrequest {
	@NotEmpty(message="Name can not be blank")
	String name;
	@NotEmpty(message="Password can not be blank")
	String password;
}
