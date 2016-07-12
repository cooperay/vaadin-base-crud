package com.cooperay.cop_domo.vaadin.mvp;

import com.cooperay.cop_domo.vaadin.WelcomeView;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SpringComponent("userView")
@ViewScope
public class UserView extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	
	public interface UserViewLinster{;
		 void addUserEvent(User user);
	}
	
	
	private UserViewLinster userViewLister;
	public UserView() {
		init();
	}
	public void addLister(UserViewLinster lister){
		this.userViewLister = lister;
	}
	
	public void updateUi(){
		addComponent(new Label("User Saved!"));
	}
	
	public void init(){
		removeAllComponents();
		final TextField name = new TextField("名称");
		addComponent(name);
		Button save = new Button("保存");
		save.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				String nString = name.getValue();
				User user = new User();
				user.setName(nString);
				user.setId(1);
				userViewLister.addUserEvent(user);
			}
		});
		addComponent(save);
		
		Button canel = new Button("取消");
		canel.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(WelcomeView.NAME);
			}
		});
		addComponent(canel);
	}
	
	
	
}
