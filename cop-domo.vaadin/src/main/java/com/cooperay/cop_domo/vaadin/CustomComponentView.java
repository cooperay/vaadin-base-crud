package com.cooperay.cop_domo.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
@SpringView(name = CustomComponentView.NAME)
public class CustomComponentView extends VerticalLayout implements View{
	public static final String NAME =  "customComponentView";

	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		MyComponent myComponent = new MyComponent();
		addComponent(myComponent);
		
	}
}
