package com.cts.payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	public static void main(String args[]) {
        SpringApplication.run(App.class, args);
    }

}


	
	
	
	
	
	
		/*
	 * import java.util.List;
	 * 
	 * import org.hibernate.Session; import org.hibernate.SessionFactory; import
	 * org.hibernate.boot.registry.StandardServiceRegistryBuilder; import
	 * org.hibernate.cfg.Configuration; import
	 * org.springframework.boot.autoconfigure.SpringBootApplication;
	 * 
	 * import com.cts.payroll.bean.Department;
	 * 
	 * 
	 * public static void main(String[] args) {
	 * 
	 * Department dep1 = new Department("DB Admin"); Department dep2 = new
	 * Department(""); System.out.println(" =======CREATE =======");
	 * create(dep1); create(dep2);
	 * 
	 * System.out.println(" =======READ ======="); List<Department> dep =
	 * read(); for (Department d : dep) { System.out.println(d);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public static SessionFactory getSessionFactory() { Configuration
	 * configuration = new Configuration().configure();
	 * configuration.addAnnotatedClass(com.cts.payroll.bean.Department.class);
	 * StandardServiceRegistryBuilder builder = new
	 * StandardServiceRegistryBuilder()
	 * .applySettings(configuration.getProperties()); SessionFactory
	 * sessionFactory = configuration.buildSessionFactory(builder.build());
	 * return sessionFactory; }
	 * 
	 * public static List<Department> read() { Session session =
	 * getSessionFactory().openSession();
	 * 
	 * @SuppressWarnings("unchecked") List<Department> departments =
	 * session.createQuery("FROM Department").list(); session.close();
	 * System.out.println("Found " + departments.size() + " departments");
	 * return departments; }
	 * 
	 * public static Integer create(Department dep) { Session session =
	 * getSessionFactory().openSession(); session.beginTransaction();
	 * session.save(dep); session.getTransaction().commit(); session.close();
	 * System.out.println("Successfully created " + dep.toString()); return
	 * dep.getId(); }
	 */


