package edu.dev.identityservice.configuration;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.dev.identityservice.entity.User;
import edu.dev.identityservice.enums.Role;
import edu.dev.identityservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ApplicationInitConfig {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	ApplicationRunner applicationRunner(UserRepository userRepository) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				var roles = new HashSet<String>();
				roles.add(Role.ADMIN.name());

				User user = User.builder().username("admin").password(passwordEncoder.encode("admin")).roles(roles)
						.build();

				userRepository.save(user);

				log.warn("admin user has been created with default password: admin, please change it.");
			}
		};
	}
}
