package dev.example.fsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class FsjavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsjavaApplication.class, args);
	}

}
