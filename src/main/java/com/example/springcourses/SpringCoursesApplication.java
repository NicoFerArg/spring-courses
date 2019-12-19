package com.example.springcourses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoursesApplication.class, args);
	}

}
