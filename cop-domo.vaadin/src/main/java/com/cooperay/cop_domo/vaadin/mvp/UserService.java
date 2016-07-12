package com.cooperay.cop_domo.vaadin.mvp;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
	
	public User save(User user){
		user.setName(user.getName()+"===>saved");
		return user;
	}
	
}
