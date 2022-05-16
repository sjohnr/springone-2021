package com.example.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Flux;

import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		// @formatter:off
		http
			.authorizeExchange((authorize) -> authorize.anyExchange().authenticated())
			.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(withDefaults()));
		// @formatter:on
		return http.build();
	}

	@Bean
	public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
		authoritiesConverter.setAuthorityPrefix("");

		ReactiveJwtAuthenticationConverter authenticationConverter = new ReactiveJwtAuthenticationConverter();
		authenticationConverter.setJwtGrantedAuthoritiesConverter((jwt) -> {
			if (!"josh".equals(jwt.getSubject())) {
				return Flux.fromIterable(authoritiesConverter.convert(jwt));
			}
			Set<String> authorities = AuthorityUtils.authorityListToSet(authoritiesConverter.convert(jwt));
			if (authorities.contains("flights:write")) {
				authorities.add("flights:approve");
			}
			if (authorities.contains("flights:read")) {
				authorities.add("flights:all");
			}
			return Flux.fromIterable(AuthorityUtils.createAuthorityList(authorities.toArray(String[]::new)));
		});

		return authenticationConverter;
	}
}
