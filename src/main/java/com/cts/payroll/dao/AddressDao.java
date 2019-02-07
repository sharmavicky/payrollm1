package com.cts.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.cts.payroll.bean.Address;
import com.cts.payroll.bean.CreateLog;
import com.cts.payroll.bean.PayrollAppException;

@Component
public class AddressDao {
	private static final String UPDATE_ADD_QUERY = "update address set ad_line1 = ?, ad_line2 = ?, ad_locality = ?, ad_pincode = ? where ad_id = ?";

	public void updateAddress(Address address) throws PayrollAppException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(UPDATE_ADD_QUERY);
			statement.setString(1, address.getAddress1());
			statement.setString(2, address.getAddress2());
			statement.setString(3, address.getLocality());
			statement.setString(4, address.getPincode());
			statement.setInt(5, address.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			CreateLog.logError(e.getMessage());
			throw new PayrollAppException("Error when updating address.\n" + e.getMessage());
		} finally {
			try {

				if (statement != null) {

					statement.close();

				}
				if (connection != null) {

					connection.close();

				}
			} catch (Exception e) {
				e.printStackTrace();
				CreateLog.logError(e.getMessage());
			}
		}
	}
}
