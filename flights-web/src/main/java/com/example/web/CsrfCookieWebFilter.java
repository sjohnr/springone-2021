package com.example.web;

import java.time.Duration;

import reactor.core.publisher.Mono;

import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.http.ResponseCookie;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

@Component
public class CsrfCookieWebFilter implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		String key = CsrfToken.class.getName();
		Mono<CsrfToken> csrfToken = null != exchange.getAttribute(key) ? exchange.getAttribute(key) : Mono.empty();
		return csrfToken.doOnSuccess(token -> {
			ResponseCookie cookie = ResponseCookie.from("XSRF-TOKEN", token.getToken()).maxAge(Duration.ofHours(1))
					.httpOnly(false).path("/").sameSite(WebFluxProperties.SameSite.LAX.attribute()).build();
			exchange.getResponse().getCookies().add("XSRF-TOKEN", cookie);
		}).then(chain.filter(exchange));
	}

}