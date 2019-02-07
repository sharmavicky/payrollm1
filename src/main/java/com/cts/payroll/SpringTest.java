package com.cts.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cts.payroll.bean.EmployeeService;
import com.cts.payroll.bean.PayrollAppException;

@Configuration
@ComponentScan
public class SpringTest {
	private EmployeeService employeeService;

	@Bean(name = "springTestInstance")
	public SpringTest getSpringTest() {
		return new SpringTest();
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	
	

	public static void main(String[] args) throws PayrollAppException {
		/*
		 * ApplicationContext context = new
		 * ClassPathXmlApplicationContext("spring-config.xml");
		 * 
		 * 
		 * Skills skill = (Skills) context.getBean("javaSkill");
		 * 
		 * Skills skill1 = (Skills) context.getBean("javaSkill"); Skills skill2
		 * = (Skills) context.getBean("javaSkill");
		 * 
		 * 
		 * System.out.println(skill1); System.out.println(skill); Skills skill2
		 * = (Skills) context.getBean("java"); System.out.println(skill2);
		 * 
		 * System.out.println(skill); Employee employee = (Employee)
		 * context.getBean("employee"); System.out.println(employee);
		 * 
		 * SpringTest springTest = (SpringTest) context.getBean("springTest");
		 * System.out.println(springTest.getEmployeeService().getEmployees());
		 */

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringTest.class);

		SpringTest springTest = (SpringTest) context.getBean("springTestInstance");
		System.out.println(springTest.getEmployeeService().getEmployees());
      
	}

}
