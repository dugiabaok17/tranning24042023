package com.example.demo.request;

import javax.validation.constraints.NotBlank;

public class DepartmentRequest {

	@NotBlank(message = "Không đưuọc để trống tên phòng ban")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
