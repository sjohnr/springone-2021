package com.example.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="rules")
public class AccessRule {
	@Id
	private String pattern;

	@Column
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
