package com.cooperay.cop_domo.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SpringView
@DesignRoot
public class LoginView extends CustomComponent implements View {
	 
	public final static String NAME = "login";
	    
	    TextField username;
	    
	    // Here we inject to the constructor and actually do
	    // not store the injected object to use it later
	    @Autowired
	    public LoginView(User user) {
	        VerticalLayout layout = new VerticalLayout();
	        layout.setMargin(true);
	        layout.setSpacing(true);
	        
	        // An input field for editing injected data
	        BeanItem<User> item = new BeanItem<User>(user);
	        username = new TextField("User name",
	            item.getItemProperty("name"));
	        username.setNullRepresentation("");
	        layout.addComponent(username);

	        // Login button (authentication omitted) / Java 8
	        layout.addComponent(new Button("Login", e ->
	            getUI().getNavigator().navigateTo(MainView.NAME)));
	        
	        setCompositionRoot(layout);
	    }
	    
	@Override
	public void enter(ViewChangeEvent event) {
		 username.setValue(null); // Always reset
		// TODO Auto-generated method stub

	}

}
