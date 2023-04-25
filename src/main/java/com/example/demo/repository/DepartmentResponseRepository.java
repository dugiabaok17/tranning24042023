package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.response.DepartmentResponse;

public interface DepartmentResponseRepository  extends JpaRepository<DepartmentResponse, Long>{
	@Query(value ="SELECT s.id,s.name FROM Department s", nativeQuery = true)
	List<DepartmentResponse> findAllDepartmentResponse();
}
