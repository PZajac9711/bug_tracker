package org.bugtracker.bugtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class BugtrackerApplication {
	public static void main(String[] args) {
		SpringApplication.run(BugtrackerApplication.class, args);
	}
}
