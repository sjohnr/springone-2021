package com.example.api;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.AuthorityReactiveAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AccessRuleAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

	private final AccessRuleRepository rules;
	private final Map<PathPattern, AuthorityReactiveAuthorizationManager<AuthorizationContext>> mappings;

	public AccessRuleAuthorizationManager(AccessRuleRepository rules) {
		this.rules = rules;
		this.mappings = new HashMap<>();
	}

	@Override
	public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
		return authentication.flatMap(x -> {
			for (Map.Entry<PathPattern, AuthorityReactiveAuthorizationManager<AuthorizationContext>> mapping
				: this.mappings.entrySet()) {
				if (mapping.getKey().matches(object.getExchange().getRequest().getPath().pathWithinApplication())) {
					AuthorityReactiveAuthorizationManager<AuthorizationContext> manager = mapping.getValue();

					return manager.check(authentication, new AuthorizationContext(object.getExchange()));
				}
			}

			return Mono.empty();
		});
	}

	@EventListener
	public void applyRules(ApplicationReadyEvent event) {


		this.rules.findAll()
			.doOnEach(rule -> {
				if (rule.get() == null) {
					return;
				}

				AccessRule r = Objects.requireNonNull(rule.get());

				this.mappings.put(
					new PathPatternParser().parse(r.getPattern()),
					AuthorityReactiveAuthorizationManager.hasAuthority(r.getAuthority())
				);
			})
			.subscribe();
	}
}
