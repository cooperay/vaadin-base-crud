package com.cooperay.cop_domo.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.cooperay.cop_domo.vaadin.mvp.UserViewHander;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;


@SpringView(name = MvpView.NAME)
public class MvpView extends VerticalLayout implements View{
	public static final String NAME = "mvpView"; 
	@Autowired
	private UserViewHander userViewHander;
	
	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		userViewHander.getUserView().addLister(userViewHander);
		addComponent(userViewHander.getUserView());
	}
	
}
