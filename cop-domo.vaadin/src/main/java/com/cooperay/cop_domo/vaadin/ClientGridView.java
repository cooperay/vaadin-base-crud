package com.cooperay.cop_domo.vaadin;

import java.util.ArrayList;
import java.util.Collection;

import com.cooperay.cop_domo.vaadin.Person.OrderState;
import com.vaadin.client.widgets.Grid;
import com.vaadin.client.widgets.Grid.HeaderRow;
import com.vaadin.client.widgets.Grid.SelectionMode;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.VerticalLayout;

public class ClientGridView extends VerticalLayout implements View {

	public static final String NAME = "gridView";
	
	public ClientGridView() {
		setMargin(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();

		 Collection<Person> people = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			Person person = new Person();
			person.setId(i);
			person.setFirstName("liyang");
			person.setLastName(i+"");
			person.setState(OrderState.CANCEL);
			people.add(person);
		}
		System.out.println("======>"+OrderState.RECEIVED.name());
		// Have a container of some type to contain the data
		BeanItemContainer<Person> container = new BeanItemContainer<Person>(Person.class,people);
		// Create a grid bound to the container
		
		Grid grid = new Grid();
		
		
		//grid.setColumnOrder("firstName", "lastName");
		
		grid.setEditorEnabled(true);
		grid.setEditorSaveCaption("保存");
		grid.setEditorCancelCaption("取消");
		grid.setHeightByRows(20);
		grid.setHeightMode(HeightMode.ROW);
		// Handle selection in single-selection mode
		grid.setSelectionMode(SelectionMode.MULTI);
		/*grid.getColumn("state").setRenderer(new AbstractRenderer<OrderState>(null) {
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return super.toString();
			}
		});*/
		HeaderRow groupingHeader = grid.prependHeaderRow();
	/*	groupingHeader.join(
		    groupingHeader.getCell("born"),
		    groupingHeader.getCell("died"),
		    groupingHeader.getCell("lived")).setText("Dates of Life");
		*/

		
	

	}

}
