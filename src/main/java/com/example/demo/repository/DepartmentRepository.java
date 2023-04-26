package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findByName(String name);

//	@Query(value ="SELECT s.name FROM Department s", nativeQuery = true)
//	List<Department> findAllDepartmentName();

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Department(name) VALUES(:name)", nativeQuery = true)
	void saveDepartment(@Param("name") String name);
	

}
