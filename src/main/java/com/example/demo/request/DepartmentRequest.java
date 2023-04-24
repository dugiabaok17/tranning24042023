package com.example.demo.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DepartmentRequest {

	@NotBlank(message = "Không đưuọc để trống tên phòng ban")
	private String name;

}
