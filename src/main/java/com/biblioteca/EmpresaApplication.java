package com.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan
@SpringBootApplication(scanBasePackages = {"com.biblioteca"})
public class EmpresaApplication{

	public static void main(String[] args) {
		SpringApplication.run(EmpresaApplication.class, args);
	}

}
