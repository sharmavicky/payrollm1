package com.cts.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.cts.payroll.bean.CreateLog;
import com.cts.payroll.bean.Employee;
import com.cts.payroll.bean.PayrollAppException;

@Component
public class EmployeeSkillDao {
	public static final String DELETE_EMP_SKILS = "delete from employee_skill where es_em_id=?";
	private static final String INSERT_EMP_SKILL = "insert into employee_skill (es_em_id, es_sk_id) values (?, ?)";
	Employee employee = null;
	Connection connection = null;
	PreparedStatement statement = null;

	public void deleteEmployeeSkills(int id) throws PayrollAppException {
		try {
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(DELETE_EMP_SKILS);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			CreateLog.logError(e.getMessage());
			throw new PayrollAppException("Error when deleting skills.\n" + e.getMessage());
		} finally {
			try {
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

	public void insertEmployeeSkills(int employeeId, String[] skillIds) throws PayrollAppException {
		try {
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(INSERT_EMP_SKILL);
			for (String skillId : skillIds) {
				statement.setInt(1, employeeId);
				statement.setInt(2, Integer.parseInt(skillId));
				statement.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			CreateLog.logError(e.getMessage());
			throw new PayrollAppException("Error when inserting skills.\n" + e.getMessage());
		} finally {
			try {

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
