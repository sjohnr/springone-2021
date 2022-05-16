package com.example.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("rules")
public class AccessRule {
	@Id
	private String pattern;

	private String authority;

	protected AccessRule() {}

	public AccessRule(String pattern, String authority) {
		this.pattern = pattern;
		this.authority = authority;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
