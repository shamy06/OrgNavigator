package com.spring.organizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title = "Organization Service Documentation",
				description = "Organization Service Microservice Documentation",
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
				description = "Organization Service Documentation",
				url = "https://www.shubham.net/organization_Service.html"
		)
)
@SpringBootApplication
public class OrganizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

}
