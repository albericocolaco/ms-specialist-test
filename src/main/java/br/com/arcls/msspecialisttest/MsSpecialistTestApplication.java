package br.com.arcls.msspecialisttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class MsSpecialistTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSpecialistTestApplication.class, args);
	}

}
