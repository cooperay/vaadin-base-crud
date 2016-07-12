package com.cooperay.cop_domo.vaadin;

import org.springframework.stereotype.Component;

import com.vaadin.spring.annotation.VaadinSessionScope;

@VaadinSessionScope
@Component
public class User {

	public User() {
		System.out.println("=====>User created");
	}
	private String username;

	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
