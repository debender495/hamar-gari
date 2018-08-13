package com.deb.gari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.deb.gari"})
public class MainApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApiApplication.class, args);
	}
}
