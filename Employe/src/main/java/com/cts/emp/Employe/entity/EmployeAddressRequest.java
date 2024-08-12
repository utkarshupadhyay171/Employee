package com.cts.emp.Employe.entity;

import com.cts.emp.Employe.dto.Addressdto;

import lombok.Data;

@Data
public class EmployeAddressRequest {
	public String username;
	public Addressdto addressDTO;
}
