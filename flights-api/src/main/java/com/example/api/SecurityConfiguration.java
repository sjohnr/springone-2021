package com.example.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeHttpRequests((authz) -> authz
				.antMatchers("/flights/all").hasAuthority("flights:all")
				.antMatchers("/flights/*/take-off").hasAuthority("flights:approve")
				.antMatchers("/flights").hasAuthority("flights:read")
				.anyRequest().hasAuthority("flights:write")
			)
			.httpBasic(Customizer.withDefaults())
			.cors(Customizer.withDefaults())
			.csrf((csrf) -> csrf
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			);
		// @formatter:on
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails josh = User.withUsername("josh")
				.password("{bcrypt}$2a$10$l4d14E8Vzm/xpOKSNi5T..pzRYzMKNN3Fxt0DNkNWZpK/eNsV86ca")
				.authorities("flights:all", "flights:approve", "flights:read", "flights:write")
				.build();
		UserDetails marcus = User.withUsername("marcus")
				.password("{bcrypt}$2a$10$l4d14E8Vzm/xpOKSNi5T..pzRYzMKNN3Fxt0DNkNWZpK/eNsV86ca")
				.authorities("flights:read", "flights:write")
				.build();
		UserDetails steve = User.withUsername("steve")
				.password("{bcrypt}$2a$10$pSOEyeq4mL6bBPAyLr1JTeitMie5NCsSO9NliAWSDdih04eC3F9IG")
				.authorities("flights:read", "flights:write")
				.build();
		return new InMemoryUserDetailsManager(josh, marcus, steve);
	}

}
