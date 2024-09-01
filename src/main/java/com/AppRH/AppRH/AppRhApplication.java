package com.AppRH.AppRH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.AppRH.AppRH.repository")
@EntityScan(basePackages = "com.AppRH.AppRH.entity")
public class AppRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppRhApplication.class, args);
	}

}
