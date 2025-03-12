package com.nqe.employee_management_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
This is the entry point java class which can be run to start the application
 */

//@SpringBootApplication(scanBasePackages = "controller")
@SpringBootApplication
//@ComponentScan(basePackages = {"controller"})
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

}
