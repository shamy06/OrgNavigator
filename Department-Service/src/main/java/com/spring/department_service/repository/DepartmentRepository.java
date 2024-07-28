package com.spring.department_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.department_service.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	Department findByDepartmentCode(String departmentCode);
}
