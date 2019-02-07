package com.cts.payroll.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cts.payroll.bean.Department;
import com.cts.payroll.bean.PayrollAppException;
import com.cts.payroll.bean.Skill;
import com.cts.payroll.dao.DepartmentDao;
import com.cts.payroll.dao.EmployeeDao;
import com.cts.payroll.dao.SkillDao;

@WebServlet("/showemployee")
public class ShowEmployee extends HttpServlet {
	private static final long serialVersionUID = 1;

	public ShowEmployee() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		EmployeeDao dao = new EmployeeDao();
		DepartmentDao departmentDao = new DepartmentDao();
		SkillDao skillDao = new SkillDao();

		String error = "";
		try {
			List<Department> departments = departmentDao.getDepartments();
			List<Skill> skills = skillDao.getSkills();
			request.setAttribute("employee", dao.getEmployee(employeeId));
			request.setAttribute("departments", departments);
			request.setAttribute("skills", skills);
		} catch (PayrollAppException e) {
			e.printStackTrace();
			error = "System error!\nPlease try after some time or contact Administrator.";
		}
		RequestDispatcher rd = request.getRequestDispatcher("updateemp.jsp");
		rd.forward(request, response);
	}

}
