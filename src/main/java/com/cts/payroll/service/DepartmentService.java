package com.cts.payroll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.payroll.bean.Department;
import com.cts.payroll.bean.PayrollAppException;
import com.cts.payroll.dao.DepartmentDao;
import com.cts.payroll.repository.DepartmentRepository;

@Service
public class DepartmentService {
	private DepartmentDao departmentDao;
	private DepartmentRepository departmentRepository;

	@Autowired
	public void setDepartmentRepository(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Autowired
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Transactional
	public List<Department> getDepartments()  {
		// return departmentDao.getDepartments();

		return (List<Department>) departmentRepository.findAll();

	}

	@Transactional
	public Department getDepartmentById(int departmentId) {

		return departmentRepository.findById(departmentId).get();
	}

}
