package com.cts.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.payroll.bean.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
	
	

}
