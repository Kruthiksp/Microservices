package com.kruthik.petistan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class MailMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(MailMicroservice.class, args);
	}

}
