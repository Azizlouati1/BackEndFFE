package com.cni.elearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cni.elearning.Models.Users.Role;
import com.cni.elearning.Models.Users.User;
import com.cni.elearning.Repositories.Users.UserRepository;


@SpringBootApplication
public class ElearningApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(ElearningApplication.class, args);
	}

	public void run(String... args) {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if (null == adminAccount) {
			User user = new User();

			user.setEmail("admin@gmail.com");
			user.setFirstname("Admin");
			user.setLastname("Admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin123"));
			userRepository.save(user);
		}
	}
}
