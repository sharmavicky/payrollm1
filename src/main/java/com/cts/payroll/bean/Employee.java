package com.cts.payroll.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "employee")
@NamedQueries({
		@NamedQuery(name = "Employee.fetchAllEmployeeDetails", query = "select distinct e from Employee e "
				+ "left join fetch e.skillList " + "join fetch e.address " + "join fetch e.department"),
		
		@NamedQuery(name = "Employee.fetchEmployeeDetails", query = "select distinct e from Employee e "
				+ "left join fetch e.skillList " + "join fetch e.address " + "join fetch e.department "
				+ "where e.id=:employeeId") })
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "em_id")
	private int id;
	@Transient
	private String skills;
	@Column(name = "em_name")
	private String name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") 
	@Temporal(TemporalType.DATE)
	@Column(name = "em_date_of_birth")
	private Date dob;
	@Column(name = "em_salary")
	private float salary;
	@Column(name = "em_gender")
	private String gender;
	@Transient
	private String email;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "em_ad_id")
	private Address address;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "em_de_id")
	private Department department;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "employee_skill", joinColumns = { @JoinColumn(name = "es_em_id") }, inverseJoinColumns = {
			@JoinColumn(name = "es_sk_id") })
	private List<Skill> skillList;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSkills() {
		skills = "";
		for (Skill skill : skillList) {
			skills += skill.getName() + ", ";
		}
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public List<Skill> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<Skill> skillList) {
		this.skillList = skillList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [skills=");
		builder.append(skills);
		builder.append(", id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", dob=");
		builder.append(dob);
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", address=");
		builder.append(address);
		builder.append(", department=");
		builder.append(department);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
