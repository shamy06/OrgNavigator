 package com.spring.employee_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title = "Employee Service Documentation",
				description = "Department Service Microservice Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Shubham",
						email = "shubham.net@gmail.com",
						url = "https://www.shubham.net"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.shubham.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Employee Service Documentation",
				url = "https://www.shubham.net/Employee_Service.html"
		)
)
@SpringBootApplication
@EnableFeignClients
public class EmployeeServiceApplication {

	
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}	
	
	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
