package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Department;
import com.example.demo.service.IDepartmentService;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
	

	private IDepartmentService iDepartmentService;
	
	private Department department;
	
	public void hehe() {
	}
	
	
	

}
