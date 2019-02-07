package com.cts.payroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.payroll.bean.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	List<Employee> fetchAllEmployeeDetails();
	Employee fetchEmployeeDetails(@Param("employeeId") int EmployeeId);

}
