package com.cooperay.cop_domo.vaadin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.xml.crypto.Data;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;

@SpringView(name=PropertySetItemView.NAME)
public class PropertySetItemView extends CustomComponent implements View{

	public static final String NAME = "propertySetView";
	
	public PropertySetItemView() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	public void init(){
		FormLayout layout = new FormLayout();
		// Have a bean
		PropertysetItem binder = new PropertysetItem();
		
		binder.addItemProperty("fristName",new ObjectProperty<String>("li"));
		binder.addItemProperty("lastName",new ObjectProperty<String>("yang"));
		binder.addItemProperty("birthday",new ObjectProperty<Date>(new Date()));
		binder.addItemProperty("select",new ObjectProperty<String>("Io"));
		
		FieldGroup fieldGroup = new FieldGroup(binder);
		
		ComboBox select = new ComboBox("科目");
		select.addItem("Io");
    	select.setItemCaption("Io","选择的直为Io");
    	select.addItem("Europa");
    	select.addItem("Ganymedes");
    	select.addItem("Callisto");
    	
    	layout.addComponent(fieldGroup.buildAndBind("姓", "fristName"));
		layout.addComponent(fieldGroup.buildAndBind("名", "lastName"));
		layout.addComponent(fieldGroup.buildAndBind("生日", "birthday"));
		fieldGroup.bind(select, "select");
    	layout.addComponent(select);
    	
		// Buffer the form content
		layout.addComponent(new Button("OK", new ClickListener() {
		    @Override
		    public void buttonClick(ClickEvent event) {
		    	try {
					fieldGroup.commit();
				} catch (CommitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	Iterator iterator = binder.getItemPropertyIds().iterator();
		    	while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					System.out.println(binder.getItemProperty(iterator.next()).getValue());
				}
		    }
		}));
		
		layout.addComponent(new Button("Discard", new ClickListener() {
		    @Override
		    public void buttonClick(ClickEvent event) {
		        Notification.show("Discarded!");
		    }
		}));
		setCompositionRoot(layout);
	}
	
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
