package com.cts.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.payroll.bean.EmployeeService;
import com.cts.payroll.bean.PayrollAppException;
import com.cts.payroll.bean.User;
import com.cts.payroll.service.UserService;

@Controller
@SessionAttributes("user")
public class LoginController {
	private UserService userService;
	private EmployeeService employeeService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/home")
	public String showLogin() {
		return "login";
	}

	@PostMapping("/authenticate")
//	@ExceptionHandler({ PayrollAppException.class })
	public String authenticate(User user, Model model) throws PayrollAppException {
		String password = user.getPassword();
		String actualPassword = null;
		User actualUser = userService.getUser(user.getUserName());
		if (actualUser != null) {
			actualPassword = userService.getUser(user.getUserName()).getPassword();
		}
		if (password.equals(actualPassword)) {
			model.addAttribute("user", user);

			model.addAttribute("employees", employeeService.getEmployees());
			return "emplist";
		} else {
			model.addAttribute("error", "Invalid username or password.");
		}
		return "login";
	}
}
