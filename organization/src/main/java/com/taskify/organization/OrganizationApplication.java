package com.taskify.organization;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class OrganizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationApplication.class, args);
	}

}
