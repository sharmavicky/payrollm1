package com.cts.payroll.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cts.payroll.bean.CreateLog;
import com.cts.payroll.bean.PayrollAppException;

public class ConnectionManager {
	public static Connection getConnection() throws PayrollAppException {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://10.230.169.116:3306/employee", "user", "admin");
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			CreateLog.logError(e.getMessage());
			throw new PayrollAppException("Error when loading JDBC driver.\n" + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			CreateLog.logError(e.getMessage());
			throw new PayrollAppException("Error when getting user data.\n" + e.getMessage());
		}
	}
}
