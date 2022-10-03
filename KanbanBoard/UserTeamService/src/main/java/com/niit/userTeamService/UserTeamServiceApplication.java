package com.niit.userTeamService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserTeamServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTeamServiceApplication.class, args);
	}

}
