package com.example.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration {

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails josh = User.withUsername("josh")
				.password("{bcrypt}$2a$10$l4d14E8Vzm/xpOKSNi5T..pzRYzMKNN3Fxt0DNkNWZpK/eNsV86ca")
				.roles("USER")
				.build();
		UserDetails marcus = User.withUsername("marcus")
				.password("{bcrypt}$2a$10$l4d14E8Vzm/xpOKSNi5T..pzRYzMKNN3Fxt0DNkNWZpK/eNsV86ca")
				.roles("USER")
				.build();
		UserDetails steve = User.withUsername("steve")
				.password("{bcrypt}$2a$10$pSOEyeq4mL6bBPAyLr1JTeitMie5NCsSO9NliAWSDdih04eC3F9IG")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(josh, marcus, steve);
	}

}
