package com.airconsole.reservation;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	Scanner scanner() {
		return new Scanner(System.in);
	}
}
