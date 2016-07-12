package com.cooperay.cop_domo.vaadin;

import java.util.Date;

import com.cooperay.cop_domo.vaadin.Person.OrderState;
import com.vaadin.client.ui.Field;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;

@SpringView(name=BeanItemView.NAME)
public class BeanItemView extends CustomComponent implements View{

	public static final String NAME = "beanItemView";
	
	public BeanItemView() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	public void init(){
		FormLayout layout = new FormLayout();
		// Have a bean
		Person bean = new Person();
		bean.setFirstName("li");
		bean.setLastName("yang");
		bean.setSelect("Io");
		bean.setBirthday(new Date());
		bean.setState(OrderState.CANCEL);

		// Form for editing the bean
		final BeanFieldGroup<Person> binder =
		        new BeanFieldGroup<Person>(Person.class);
		binder.setItemDataSource(bean);
		layout.addComponent(binder.buildAndBind("姓", "firstName"));
		layout.addComponent(binder.buildAndBind("名", "lastName"));
		layout.addComponent(binder.buildAndBind("生日", "birthday"));
		layout.addComponent(binder.buildAndBind("状态","state",ComboBox.class));
		
		ComboBox select = new ComboBox("科目");
		select.addItem("Io");
    	select.setItemCaption("Io","选择的直为Io");
    	select.addItem("Europa");
    	select.addItem("Ganymedes");
    	select.addItem("Callisto");
    	binder.bind(select,"select");
    	
    	layout.addComponent(select);
    	
    	
		// Buffer the form content
		binder.setBuffered(true);
		layout.addComponent(new Button("OK", new ClickListener() {
		    @Override
		    public void buttonClick(ClickEvent event) {
		        try {
		        	binder.commit();
		        	System.out.println(bean.getFirstName()+bean.getLastName()+bean.getSelect());
		        	Notification.show("commited");
		        	//System.out.println(binder.getItemDataSource().getBean().getFirstName());
		        } catch (CommitException e) {
		        }
		    }
		}));
		
		layout.addComponent(new Button("Discard", new ClickListener() {
		    @Override
		    public void buttonClick(ClickEvent event) {
		        binder.discard();
		        System.out.println(bean.getFirstName());
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
