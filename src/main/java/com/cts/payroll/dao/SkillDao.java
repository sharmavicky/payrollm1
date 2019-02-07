package com.cts.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.cts.payroll.bean.CreateLog;
import com.cts.payroll.bean.PayrollAppException;
import com.cts.payroll.bean.Skill;

@Component
public class SkillDao {
	public static final String GET_ALL_SK_QUERY = "select * from skill";

	public List<Skill> getSkills() throws PayrollAppException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Skill> skills = new ArrayList<Skill>();
		try {
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(GET_ALL_SK_QUERY);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Skill skill = new Skill();
				skill.setId(resultSet.getInt("SK_ID"));
				skill.setName(resultSet.getString("SK_NAME"));
				skills.add(skill);
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
		return skills;

	}
}
