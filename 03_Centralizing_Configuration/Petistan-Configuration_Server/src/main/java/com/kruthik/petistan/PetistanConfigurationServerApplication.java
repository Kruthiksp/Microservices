package com.kruthik.petistan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class PetistanConfigurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetistanConfigurationServerApplication.class, args);
	}

}
