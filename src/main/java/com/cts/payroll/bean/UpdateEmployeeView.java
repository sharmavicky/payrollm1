package com.cts.payroll.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class UpdateEmployeeView {

	
	private Employee employee;
	private List<Department> departments;
	private List<Skill> skills;
	public Employee getEmployee() {
		return employee;
	}
	@Autowired
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public List<Department> getDepatrtments() {
		return departments;
	}
	@Autowired
	public void setDepartments(List<Department> depatrtments) {
		this.departments = depatrtments;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	@Autowired
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
}
