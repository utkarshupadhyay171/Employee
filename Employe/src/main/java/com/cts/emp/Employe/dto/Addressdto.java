package com.cts.emp.Employe.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Addressdto {
//	@NotEmpty(message="City can not be blank")
	public String city;
//	@NotEmpty(message="State can not be blank")
	public String state;
}
