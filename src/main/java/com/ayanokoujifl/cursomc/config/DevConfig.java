package com.ayanokoujifl.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ayanokoujifl.cursomc.services.DBService;
import com.ayanokoujifl.cursomc.services.EmailService;
import com.ayanokoujifl.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (!"create".equals(strategy)) {
			return false;
		} else {
			dbService.instantiateTestDatabase();
			return true;
		}
	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
