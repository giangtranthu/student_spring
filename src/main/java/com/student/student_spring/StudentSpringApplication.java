package com.student.student_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class StudentSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentSpringApplication.class, args);
	}

}
