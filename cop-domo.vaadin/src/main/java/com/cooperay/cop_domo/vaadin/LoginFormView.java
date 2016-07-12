package com.cooperay.cop_domo.vaadin;

import com.ejt.vaadin.loginform.DefaultVerticalLoginForm;
import com.ejt.vaadin.loginform.LoginForm;
import com.ejt.vaadin.loginform.LoginForm.LoginEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;

@SpringComponent("loginFormView")
@UIScope
public class LoginFormView extends CustomComponent{

	public static final String NAME="loginFormView";
	
	public LoginFormView() {
		init();
	}
	
	public void init() {
		
		DefaultVerticalLoginForm loginForm = new DefaultVerticalLoginForm();
		loginForm.addLoginListener(new LoginForm.LoginListener() {
			@Override
			public void onLogin(LoginEvent event) {
				System.out.println(event.getUserName());
				if("admin".equals(event.getUserName())){
					User user = new User();
					user.setUsername(event.getUserName());
					VaadinSession.getCurrent().setAttribute(User.class.getName(),user );
					//Page.getCurrent().reload();
					MyUI myUI = (MyUI)getUI();
					myUI.updateContent();
				}else{
					Notification.show("login error");
				}
				
			}
		});
		setCompositionRoot(loginForm);
	}

}
