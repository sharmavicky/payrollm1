package com.cts.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.cts.payroll.bean.CreateLog;
import com.cts.payroll.bean.PayrollAppException;
import com.cts.payroll.bean.User;

@Component
public class UserDao {
	public User getUser(String userName) throws PayrollAppException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = ConnectionManager.getConnection();
			String query = "select * from user where us_username = ?";

			statement = connection.prepareStatement(query);
			statement.setString(1, userName);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("US_ID"));
				user.setUserName(resultSet.getString("US_USERNAME"));
				user.setPassword(resultSet.getString("US_PASSWORD"));
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
		return user;
	}
}