package com.cooperay.cop_domo.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = PanelView.NAME)
public class PanelView extends CustomComponent implements View {

	public static final String NAME =  "panelView";
	
	
	public PanelView() {
		 Panel panel = new Panel("新建用户档案");
		
		GridLayout gridLayout = new GridLayout(3, 3);
		gridLayout.setSizeFull();
		VerticalLayout panelContent = new VerticalLayout();
		  
		
		
		panel.setIcon(FontAwesome.AMAZON);
	     VerticalLayout layout = new VerticalLayout();
	     
	     panelContent.setMargin(true);
	     
	     PorsonForm porsonForm = new PorsonForm();
	     porsonForm.addSubmitListener(new PorsonForm.FormSubmitListener<Button.ClickEvent>(){
			@Override
			public void submit(ClickEvent entry) {
				 Notification.show("警告","Form提交按钮被点击！",Notification.Type.ERROR_MESSAGE);
			}
	    	 
	     });
	     panelContent.addComponent(porsonForm);
	     panel.setContent(panelContent);
	     gridLayout.addComponent(panel,1,1);
	     gridLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
	  
	     
	    // layout.setMargin(true);
	        
	        layout.addComponent(panel);

	        setCompositionRoot(layout);
		
	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {

	}

}
