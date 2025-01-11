package com.otakumap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OtakumapApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtakumapApplication.class, args);
	}

}
