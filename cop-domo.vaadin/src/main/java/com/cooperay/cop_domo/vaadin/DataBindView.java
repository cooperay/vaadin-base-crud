package com.cooperay.cop_domo.vaadin;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

@SpringView(name = DataBindView.NAME)
public class DataBindView extends CustomComponent implements View {

	public static final String NAME="dataBindView";

	public DataBindView() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init(){
		PropertysetItem item = new PropertysetItem();
		item.addItemProperty("name", new ObjectProperty<String>("Zaphod"));
		item.addItemProperty("age", new ObjectProperty<Integer>(42));
		
		FormLayout form = new FormLayout();

		TextField nameField = new TextField("Name");
		form.addComponent(nameField);

		TextField ageField = new TextField("Age");
		form.addComponent(ageField);
		
		Button submit  = new Button("submit",e -> System.out.println(item) );
		form.addComponent(submit);
		
		
		FieldGroup binder = new FieldGroup(item);
		binder.bind(nameField, "name");
		binder.bind(ageField, "age");
		setCompositionRoot(form);
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}
