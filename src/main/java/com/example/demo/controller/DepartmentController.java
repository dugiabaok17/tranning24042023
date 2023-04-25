package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Department;
import com.example.demo.request.DepartmentRequest;
import com.example.demo.response.DepartmentResponse;
import com.example.demo.response.ResponseObject;
import com.example.demo.service.IDepartmentService;
import com.example.demo.service.impl.DepartmentServiceImpl;


@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
	
	
	@Autowired
	private IDepartmentService iDepartmentService;
	
	@Autowired
	private DepartmentServiceImpl departmentServiceImpl;
	
	
	@GetMapping("/findAll")
	private List<DepartmentResponse> findAllDepartmentName() {
		return iDepartmentService.findAll();
	}
	
	@PostMapping
	private ResponseEntity<ResponseObject> save(@RequestBody @Valid DepartmentRequest departmentRequest) {
		return iDepartmentService.save(departmentRequest);
	}
	
    @DeleteMapping("/{id}")
	private ResponseEntity<ResponseObject> delete(@PathVariable Long id) {
		return iDepartmentService.delete(id);
	}
	
    @PutMapping("/{id}")
   	private ResponseEntity<ResponseObject> update(@PathVariable String id, @RequestBody @Valid DepartmentRequest departmentRequest) {
   		return iDepartmentService.update(Long.parseLong(id), departmentRequest);
   	}
    
    @GetMapping
    public ResponseEntity<Page<Department>> getAllDepartmentResponse(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy) 
    {
    	Page<Department> list = iDepartmentService.getAllDepartments(pageNo, pageSize, sortBy);

        return new ResponseEntity<Page<Department>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
    
    
//    @GetMapping("/department")
//    public ResponseEntity<Page<Department>> getAllDepartmentResponses(
//                        @RequestParam(defaultValue = "0") Integer pageNo, 
//                        @RequestParam(defaultValue = "10") Integer pageSize,
//                        @RequestParam(defaultValue = "id") String sortBy) 
//    {
//        Page<Department> list = departmentServiceImpl.getAllDepartment(pageNo, pageSize, sortBy);
//
//        return new ResponseEntity<Page<Department>>(list, new HttpHeaders(), HttpStatus.OK); 
//    }
}
