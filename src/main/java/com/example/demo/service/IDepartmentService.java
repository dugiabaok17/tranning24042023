package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Department;
import com.example.demo.request.DepartmentRequest;
import com.example.demo.response.DepartmentResponse;
import com.example.demo.response.ResponseObject;

public interface IDepartmentService {
	List<DepartmentResponse> findAll();
	
	Department findByName(String name);
	
	Department findById(Long id);
	
	ResponseEntity<ResponseObject> save(DepartmentRequest departmentRequest);
	
	ResponseEntity<ResponseObject> update(Long id, DepartmentRequest departmentRequest);
	
	ResponseEntity<ResponseObject> delete(Long id);
	
	Page<Department> getAllDepartments(Integer pageNo, Integer pageSize, String sortBy);
	
	DepartmentResponse findByNames(String name);
}
