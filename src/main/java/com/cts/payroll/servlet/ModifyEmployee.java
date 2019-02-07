package com.cts.payroll.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cts.payroll.bean.Address;
import com.cts.payroll.bean.Department;
import com.cts.payroll.bean.Employee;
import com.cts.payroll.bean.PayrollAppException;
import com.cts.payroll.dao.AddressDao;
import com.cts.payroll.dao.DepartmentDao;
import com.cts.payroll.dao.EmployeeDao;
import com.cts.payroll.dao.EmployeeSkillDao;
import com.cts.payroll.dao.SkillDao;

@WebServlet("/modifyemployee")
public class ModifyEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public ModifyEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error="";
		Employee employee=new  Employee();
		employee.setId(Integer.parseInt(request.getParameter("employeeId")));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			employee.setDob(simpleDateFormat.parse(request.getParameter("dob")));
		} catch (ParseException e) {
			e.printStackTrace();
			error = "Invalid date format";
			RequestDispatcher rd = request.getRequestDispatcher("updateemp.jsp");
			rd.forward(request, response);
			return;
		}
		
		employee.setName(request.getParameter("name"));
		employee.setSalary(Float.parseFloat(request.getParameter("salary")));
		
		Address address=new Address();
		address.setId(Integer.parseInt(request.getParameter("addressId")));
		address.setAddress1(request.getParameter("addressline1"));
		address.setAddress2(request.getParameter("addressline2"));
		address.setLocality(request.getParameter("locality"));
		address.setPincode(request.getParameter("pincode"));
		employee.setAddress(address);
		
		Department department =new Department();
		department.setId(Integer.parseInt(request.getParameter("departmentId")));
		employee.setDepartment(department);
		
		System.out.println(employee);
		String[] skillIds = request.getParameterValues("skill");
		for(String skillId: skillIds){
			System.out.println(skillId);
		}
		EmployeeDao employeeDao = new EmployeeDao();
		AddressDao addressDao = new AddressDao();
		EmployeeSkillDao employeeSkillDao = new EmployeeSkillDao();
		DepartmentDao departmentDao = new DepartmentDao();
		SkillDao skillDao = new SkillDao();
		try {
			employeeDao.updateEmployee(employee);
			addressDao.updateAddress(address);
			int employeeId = employee.getId();
			employeeSkillDao.deleteEmployeeSkills(employeeId);
			employeeSkillDao.insertEmployeeSkills(employeeId, skillIds);
			request.setAttribute("employee", employeeDao.getEmployee(employeeId));
			request.setAttribute("departments", departmentDao.getDepartments());
			request.setAttribute("skills", skillDao.getSkills());
			request.setAttribute("success", "Employee details saved successfully.");
		} catch (PayrollAppException e) {
			error = "System Error. Please retry or contact system administrator.";
		}
		request.setAttribute("error", error);
		RequestDispatcher rd = request.getRequestDispatcher("billgenerator.jsp");
		rd.forward(request, response);
	}


}
