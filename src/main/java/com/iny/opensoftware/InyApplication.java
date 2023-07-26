package com.iny.opensoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@ComponentScan(basePackages = "com.iny.opensoftware")
public class InyApplication {
	public static void main(String[] args) {
		SpringApplication.run(InyApplication.class, args);
	}
}
