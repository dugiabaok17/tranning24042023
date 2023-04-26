package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.DepartmentResponseRepository;
import com.example.demo.request.DepartmentRequest;
import com.example.demo.response.DepartmentResponse;
import com.example.demo.response.ResponseObject;
import com.example.demo.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private DepartmentResponseRepository departmentResponseRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<DepartmentResponse> findAll() {

		List<Department> users = departmentRepository.findAll();
		List<DepartmentResponse> userDtos = users.stream().map(user -> mapper.map(user, DepartmentResponse.class))
				.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public ResponseEntity<ResponseObject> save(DepartmentRequest departmentRequest) {
		if (findByName(departmentRequest.getName()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseObject("fail", "Không được để trùng tên department", 1, ""));
		}
//		Department department = new Department();
//		department.setName(departmentRequest.getName());
		departmentRepository.saveDepartment(departmentRequest.getName());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseObject("ok", "Thêm mới thành công", 0, departmentRequest));
	}

	@Override
	public ResponseEntity<ResponseObject> update(Long id, DepartmentRequest departmentRequest) {
		Department department = this.findById(id);
		if (department == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseObject("fail", "KHông tìm thấy phòng ban với id: " + id, 1, ""));
		}
		if (findByName(departmentRequest.getName()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseObject("fail", "Không được để trùng tên department", 1, ""));
		}
		department.setName(departmentRequest.getName());
		departmentRepository.save(department);

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Update thành công", 0, department));
	}

	@Override
	public ResponseEntity<ResponseObject> delete(Long id) {
		Department department = this.findById(id);
		if (department == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseObject("fail", "KHông tìm thấy phòng ban với id: " + id, 1, ""));
		}
		departmentRepository.delete(department);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Xóa thành công", 0, department));
	}

	@Override
	public Department findByName(String name) {
		Department department = departmentRepository.findByName(name);
		return department;
	}

	@Override
	public Department findById(Long id) {
		Optional<Department> department = departmentRepository.findById(id);
		if (department.isEmpty()) {
			return null;
		}
		return department.get();
	}

	public Page<Department> getAllDepartments(Integer pageNo, Integer pageSize, String sortBy) {

		Pageable paging = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));

		Page<Department> pagedResult = departmentRepository.findAll(paging);

		return pagedResult;
	}

	@Override
	public DepartmentResponse findByNames(String name) {
		// Lấy User entity ra từ DB
		Department user = departmentRepository.findByName(name);

		// Map thành DTO
		DepartmentResponse userDto = mapper.map(user, DepartmentResponse.class);

		return userDto;
	}

}
