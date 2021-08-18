package com.example.api;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AccessRuleAuthorizationManager access) throws Exception {
		// @formatter:off
		http
			.authorizeHttpRequests((authz) -> authz.anyRequest().access(access))
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
		// @formatter:on
		return http.build();
	}

	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
		authoritiesConverter.setAuthorityPrefix("");

		JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
		authenticationConverter.setJwtGrantedAuthoritiesConverter((jwt) -> {
			if (!"josh".equals(jwt.getSubject())) {
				return authoritiesConverter.convert(jwt);
			}
			Set<String> authorities = AuthorityUtils.authorityListToSet(authoritiesConverter.convert(jwt));
			if (authorities.contains("flights:write")) {
				authorities.add("flights:approve");
			}
			if (authorities.contains("flights:read")) {
				authorities.add("flights:all");
			}
			return AuthorityUtils.createAuthorityList(authorities.toArray(String[]::new));
		});

		return authenticationConverter;
	}

}
