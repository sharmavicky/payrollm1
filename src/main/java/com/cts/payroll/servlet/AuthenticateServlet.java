package com.cts.payroll.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cts.payroll.bean.Employee;
import com.cts.payroll.bean.PayrollAppException;
import com.cts.payroll.bean.User;
import com.cts.payroll.dao.EmployeeDao;
import com.cts.payroll.dao.UserDao;

public class AuthenticateServlet extends HttpServlet {
	public void init() throws ServletException {
		System.out.println("Inside init() method..");
	}

	public void destroy() {
		System.out.println("Inside destroy() method..");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String error = "";
		String forwardUrl = "login.jsp";
		;
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserDao dao = new UserDao();
			User user = dao.getUser(username);

			if (user != null && user.getPassword().equals(password)) {
				EmployeeDao employeeDao = new EmployeeDao();
				List<Employee> employees = employeeDao.getEmployees();

				request.setAttribute("employees", employees);

				forwardUrl = "emplist.jsp";
			} else {
				error = "Invalid Credentials!";
				request.setAttribute("userName", username);
			}
		} catch (PayrollAppException e) {
			e.printStackTrace();
			error = "System error!\nPlease try after some time or contact Administrator.";
			forwardUrl = "login.jsp";
		}
		request.setAttribute("error", error);
		RequestDispatcher rd = request.getRequestDispatcher(forwardUrl);
		rd.forward(request, response);
	}
}