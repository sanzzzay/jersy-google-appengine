package com.perpule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.perpule"})
@EnableAutoConfiguration
public class PerpuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerpuleApplication.class, args);
	}
}
