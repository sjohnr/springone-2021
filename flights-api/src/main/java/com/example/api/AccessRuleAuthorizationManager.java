package com.example.api;

import java.util.function.Supplier;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import static org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager.Builder;
import static org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager.builder;

@Component
public class AccessRuleAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

	private final AccessRuleRepository rules;
	private RequestMatcherDelegatingAuthorizationManager delegate;

	public AccessRuleAuthorizationManager(AccessRuleRepository rules) {
		this.rules = rules;
	}

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
		return this.delegate.check(authentication, object.getRequest());
	}

	@EventListener
	void applyRules(ApplicationReadyEvent event) {
		Builder builder = builder();
		for (AccessRule rule : this.rules.findAll()) {
			builder.add(
					new AntPathRequestMatcher(rule.getPattern()),
					AuthorityAuthorizationManager.hasAuthority(rule.getAuthority())
			);
		}
		this.delegate = builder.build();
	}
}
