package com.jwt.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jwt")
public class SpringSecurityWithJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityWithJwtApplication.class, args);
	}

}
//@EnableEurekaClient