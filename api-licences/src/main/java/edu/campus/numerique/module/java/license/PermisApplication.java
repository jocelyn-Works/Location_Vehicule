package edu.campus.numerique.module.java.license;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
public class PermisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PermisApplication.class, args);
	}

}
