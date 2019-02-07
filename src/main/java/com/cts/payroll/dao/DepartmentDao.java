package com.cts.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cts.payroll.bean.CreateLog;
import com.cts.payroll.bean.Department;
import com.cts.payroll.bean.PayrollAppException;
@Component
public class DepartmentDao {
	public static final String GET_ALL_DPT_QUERY = "select * from department";

	public List<Department> getDepartments() throws PayrollAppException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Department> departments = new ArrayList<Department>();
		try {
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(GET_ALL_DPT_QUERY);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Department department = new Department();
				department.setId(resultSet.getInt("DE_ID"));
				department.setName(resultSet.getString("DE_NAME"));
				departments.add(department);
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
		return departments;

	}
}
