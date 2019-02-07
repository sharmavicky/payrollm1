package com.cts.payroll.bean;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.payroll.dao.AddressDao;
import com.cts.payroll.dao.EmployeeDao;
import com.cts.payroll.dao.EmployeeSkillDao;
import com.cts.payroll.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeDao employeeDao;
	private AddressDao addressDao;
	private EmployeeSkillDao employeeSkillDao;
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Autowired
	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	@Autowired
	public void setEmployeeSkillDao(EmployeeSkillDao employeeSkillDao) {
		this.employeeSkillDao = employeeSkillDao;
	}

	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Transactional
	public List<Employee> getEmployees()  {

		//return employeeDao.getEmployees();
		return employeeRepository.fetchAllEmployeeDetails();
	}

	@Transactional
	public Employee getEmployee(int employeeId) {

		//return employeeDao.getEmployee(id);
		return employeeRepository.fetchEmployeeDetails(employeeId);
	}
	@Transactional
	public void updateEmployee(Employee employee) throws PayrollAppException {
		
		employeeRepository.save(employee);
		//employeeDao.updateEmployee(employee);
		//addressDao.updateAddress(employee.getAddress());
		//employeeSkillDao.deleteEmployeeSkills(employee.getId());
		//employeeSkillDao.insertEmployeeSkills(employee.getId(),skills);

	}
	
	
	
}
