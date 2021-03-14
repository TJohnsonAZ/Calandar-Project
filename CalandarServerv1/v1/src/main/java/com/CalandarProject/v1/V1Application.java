package com.CalandarProject.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class V1Application {

	public static Calendar calendar = new Calendar();
	
	public static void main(String[] args) {
		SpringApplication.run(V1Application.class, args);
		calendar.addMonth();
	}

}
