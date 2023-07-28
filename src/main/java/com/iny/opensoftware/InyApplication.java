package com.iny.opensoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.iny.opensoftware")
public class InyApplication {
	public static void main(String[] args) {
		SpringApplication.run(InyApplication.class, args);
	}
}