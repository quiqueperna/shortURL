package com.farmu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.shortenerurl.infraestructure", "com.shortenerurl.application"})
public class FarmuInterviewApplication {
	public static void main(String[] args) {
		SpringApplication.run(FarmuInterviewApplication.class, args);
	}

}
