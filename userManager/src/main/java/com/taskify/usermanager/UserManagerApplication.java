package com.taskify.usermanager;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class UserManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagerApplication.class, args);
	}

}
