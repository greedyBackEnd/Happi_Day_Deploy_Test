package com.happiday.Happi_Day;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HappiDayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappiDayApplication.class, args);
	}

}
