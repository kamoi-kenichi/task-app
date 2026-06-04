package com.kenichikamoi.taskapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;

import com.kenichikamoi.taskapp.entity.SiteUser;
import com.kenichikamoi.taskapp.repository.SiteUserRepository;

@SpringBootApplication
public class TaskAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner initUser(
			SiteUserRepository repository,
			PasswordEncoder passwordEncoder,
			@Value("${app.admin.username}") String adminUsername,
			@Value("${app.admin.password}") String adminPassword) {

		return args -> {
			if (repository.count() == 0) {
				SiteUser user = new SiteUser();
				user.setUsername(adminUsername);

				user.setPassword(passwordEncoder.encode(adminPassword));

				repository.save(user);
			}
		};
	}
}