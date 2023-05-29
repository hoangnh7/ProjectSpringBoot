package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class 	DemoApplication {

	public static void main(String[] args) {

			new SpringApplicationBuilder(DemoApplication.class)
					.web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
					.run(args);
		}

}
