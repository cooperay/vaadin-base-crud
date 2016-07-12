package com.cooperay.cop_domo.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;

@SpringView
public class MainView extends CustomComponent implements View {

	 public final static String NAME = "";
	    User user;
	    
	    Label greeting = new Label();
	    
	    @Autowired
	    public MainView(User user) {
	        this.user = user;

	        VerticalLayout layout = new VerticalLayout();
	        layout.setMargin(true);
	        layout.addComponent(greeting);

	        Button button = new Button("退出系统");
	        button.setDescription("点击退出系统");
	        
	        Button dataBindButton = new Button("数据绑定");
	        
	        Button beanItemButton = new Button("BeanItem绑定测试");
	        
	        Button propertySetButton = new Button("PropertySet测试");
	        
	        Button menubarButton = new Button("menuBar测试");
	        
	        menubarButton.addClickListener(e-> getUI().getNavigator().navigateTo(MenuBarView.NAME));
	        
	        propertySetButton.addClickListener(e -> getUI().getNavigator().navigateTo(PropertySetItemView.NAME));
	        
	        
	        beanItemButton.addClickListener(e -> getUI().getNavigator().navigateTo(BeanItemView.NAME));
	        
	        dataBindButton.addClickListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					getUI().getPage().setUriFragment("!"+DataBindView.NAME);
					//getUI().getNavigator().navigateTo(DataBindView.NAME);
				}
			});
	        
	        // On logout, navigate back to the login view
	        button.addClickListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					getUI().getNavigator().navigateTo(LoginView.NAME);
				}
			});
	        layout.addComponent(button);
	        layout.addComponent(dataBindButton);
	        layout.addComponent(beanItemButton);
	        layout.addComponent(propertySetButton);
	        layout.addComponent(menubarButton);
	        layout.addComponent(new Button("panel", e ->
            getUI().getNavigator().navigateTo(PanelView.NAME)));

	        
	        setCompositionRoot(layout);
	    }
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		 greeting.setValue("Hello, " + user.getName());

	}
	


}
