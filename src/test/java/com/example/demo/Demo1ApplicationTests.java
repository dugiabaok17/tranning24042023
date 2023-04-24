package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = Demo1Application.class)
class Demo1ApplicationTests {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Test
	public void testFindById() {
		Department employee = getEmployee();
		departmentRepository.save(employee);
		Department result = departmentRepository.findById(employee.getId()).get();
		assertEquals(employee.getId(), result.getId());
	}

	@Test
	public void testFindAll() {
		Department employee = getEmployee();
		departmentRepository.save(employee);
		List<Department> result = new ArrayList<>();
		departmentRepository.findAll().forEach(e -> result.add(e));
		assertEquals(result.size(), 1);
	}

	@Test
	public void testSave() {
		Department employee = getEmployee();
		departmentRepository.save(employee);
		Department found = departmentRepository.findById(employee.getId()).get();
		assertEquals(employee.getId(), found.getId());
	}

	@Test
	public void testDeleteById() {
		Department employee = getEmployee();
		departmentRepository.save(employee);
		departmentRepository.deleteById(employee.getId());
		List<Department> result = new ArrayList<>();
		departmentRepository.findAll().forEach(e -> result.add(e));
		assertEquals(result.size(), 0);
	}

	private Department getEmployee() {
		Department employee = new Department();
		employee.setName("Mahesh");
		return employee;
	}

}
