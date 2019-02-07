package com.cts.payroll.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cts.payroll.bean.Address;
import com.cts.payroll.bean.CreateLog;
import com.cts.payroll.bean.Department;
import com.cts.payroll.bean.Employee;
import com.cts.payroll.bean.PayrollAppException;

@Component
public class EmployeeDao {
	public static final String GET_ALL_EMP_QUERY = "select em_id, ad_id, em_name, em_date_of_birth, em_email, em_gender, em_salary, de_id, de_name, ad_line1, ad_line2, ad_locality,ad_pincode, group_concat(distinct sk_name separator ',') SKILLS from (((((employee INNER JOIN address on em_ad_id=ad_id) INNER JOIN  department ON em_de_id=de_id)) INNER JOIN  employee_skill on em_id = es_em_id) INNER JOIN skill on es_sk_id = sk_id) group by em_id, em_name, em_date_of_birth, em_gender,em_email, de_id, em_salary, de_name, ad_id, ad_line1, ad_line2, ad_locality, ad_pincode";

	public List<Employee> getEmployees() throws PayrollAppException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Employee> employees = new ArrayList<Employee>();
		try {
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(GET_ALL_EMP_QUERY);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("EM_ID"));
				employee.setName(resultSet.getString("EM_NAME"));
				employee.setDob(resultSet.getDate("EM_DATE_OF_BIRTH"));
				employee.setSalary(resultSet.getFloat("EM_SALARY"));
				employee.setEmail(resultSet.getString("EM_EMAIL"));
				employee.setGender(resultSet.getString("EM_GENDER"));
				employee.setSkills(resultSet.getString("SKILLS"));

				Address address = new Address();
				address.setId(resultSet.getInt("AD_ID"));
				address.setAddress1(resultSet.getString("AD_LINE1"));
				address.setAddress2(resultSet.getString("AD_LINE2"));
				address.setLocality(resultSet.getString("AD_LOCALITY"));
				address.setPincode(resultSet.getString("AD_PINCODE"));

				Department department = new Department();
				department.setId(resultSet.getInt("DE_ID"));
				department.setName(resultSet.getString("DE_NAME"));

				employee.setAddress(address);
				employee.setDepartment(department);
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CreateLog.logError(e.getMessage());
			throw new PayrollAppException("Error when getting user data.\n" + e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();

				}
				if (statement != null) {

					statement.close();

				}
				if (connection != null) {

					connection.close();

				}
			} catch (SQLException e) {
				try {
					CreateLog.logError(e.getMessage());
				} catch (PayrollAppException e1) {
					e1.printStackTrace();
					CreateLog.logError(e.getMessage());
					throw new PayrollAppException("Error when closing connection.\n" + e.getMessage());
				}
				e.printStackTrace();
			}

		}
		return employees;

	}

	public static final String GET_EMP_QUERY = "select em_id, ad_id, em_name, em_date_of_birth, em_email, em_gender, em_salary, de_id, de_name, ad_line1, ad_line2, ad_locality,ad_pincode, group_concat(distinct sk_name separator ',') SKILLS from (((((employee INNER JOIN address on em_ad_id=ad_id) INNER JOIN  department ON em_de_id=de_id)) INNER JOIN  employee_skill on em_id = es_em_id) INNER JOIN skill on es_sk_id = sk_id) where em_id= ? group by em_id, em_name, em_date_of_birth, em_gender,em_email, de_id, em_salary, de_name, ad_id, ad_line1, ad_line2, ad_locality, ad_pincode";

	public Employee getEmployee(int id) throws PayrollAppException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Employee employee = null;

		try {
			connection = ConnectionManager.getConnection();

			statement = connection.prepareStatement(GET_EMP_QUERY);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getInt("EM_ID"));
				employee.setName(resultSet.getString("EM_NAME"));
				employee.setDob(resultSet.getDate("EM_DATE_OF_BIRTH"));
				employee.setSalary(resultSet.getFloat("EM_SALARY"));
				employee.setEmail(resultSet.getString("EM_EMAIL"));
				employee.setGender(resultSet.getString("EM_GENDER"));
				employee.setSkills(resultSet.getString("SKILLS"));

				Address address = new Address();
				address.setId(resultSet.getInt("AD_ID"));
				address.setAddress1(resultSet.getString("AD_LINE1"));
				address.setAddress2(resultSet.getString("AD_LINE2"));
				address.setLocality(resultSet.getString("AD_LOCALITY"));
				address.setPincode(resultSet.getString("AD_PINCODE"));

				Department department = new Department();
				department.setId(resultSet.getInt("DE_ID"));
				department.setName(resultSet.getString("DE_NAME"));

				employee.setAddress(address);
				employee.setDepartment(department);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			CreateLog.logError(e.getMessage());
			throw new PayrollAppException("Error when getting user data.\n" + e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();

				}
				if (statement != null) {

					statement.close();

				}
				if (connection != null) {

					connection.close();

				}
			} catch (SQLException e) {
				try {
					CreateLog.logError(e.getMessage());
				} catch (PayrollAppException e1) {
					e1.printStackTrace();
					CreateLog.logError(e.getMessage());
					throw new PayrollAppException("Error when closing connection.\n" + e.getMessage());
				}
				e.printStackTrace();
			}

		}
		return employee;

	}

	public static final String UPDATE_EMP_QUERY = "update employee set em_name= ?, em_date_of_birth=?, em_salary=?, em_de_id=?, em_gender=?, em_ad_id=? where em_id = ?";

	public void updateEmployee(Employee employee) throws PayrollAppException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionManager.getConnection();

			statement = connection.prepareStatement(UPDATE_EMP_QUERY);
			statement.setString(1, employee.getName());
			Date dateOfBirth = new Date(employee.getDob().getTime());
			statement.setDate(2, dateOfBirth);
			statement.setFloat(3, employee.getSalary());
			statement.setInt(4, employee.getDepartment().getId());
			statement.setString(5, employee.getGender());
			statement.setInt(6, employee.getAddress().getId());
			statement.setInt(7, employee.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			CreateLog.logError(e.getMessage());
			throw new PayrollAppException("Error when getting updating employee.\n" + e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();

				}
				if (statement != null) {

					statement.close();

				}
				if (connection != null) {

					connection.close();

				}
			} catch (SQLException e) {
				try {
					CreateLog.logError(e.getMessage());
				} catch (PayrollAppException e1) {
					e1.printStackTrace();
					CreateLog.logError(e.getMessage());
					throw new PayrollAppException("Error when closing connection.\n" + e.getMessage());
				}
				e.printStackTrace();
			}

		}

	}
}