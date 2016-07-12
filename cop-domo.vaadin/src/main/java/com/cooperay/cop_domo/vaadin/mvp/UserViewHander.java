package com.cooperay.cop_domo.vaadin.mvp;

import org.springframework.beans.factory.annotation.Autowired;

import com.cooperay.cop_domo.vaadin.mvp.UserView.UserViewLinster;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

@SpringComponent("userViewHander")
@ViewScope
public class UserViewHander implements UserViewLinster{

	@Autowired
	private UserService userService;
	@Autowired
	private UserView userView;
	
	public UserViewHander() {
		System.out.println("=======>user view hander created");
	}
	
	@Override
	public void addUserEvent(User user) {
		User user2 = userService.save(user);
		System.out.println(user2.getName());
		userView.updateUi();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}
	
	
	
}
