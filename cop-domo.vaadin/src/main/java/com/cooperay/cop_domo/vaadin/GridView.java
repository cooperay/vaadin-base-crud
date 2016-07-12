package com.cooperay.cop_domo.vaadin;

import java.util.ArrayList;
import java.util.Collection;

import com.cooperay.cop_domo.vaadin.Person.OrderState;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.ClientConnector.DetachEvent;
import com.vaadin.server.ErrorMessage.ErrorLevel;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.CommitErrorEvent;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SpringView(name = GridView.NAME)
public class GridView extends VerticalLayout implements View {

	public static final String NAME = "gridView";
	
	public GridView() {
		setMargin(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("====>gridview");
		removeAllComponents();

		 Collection<Person> people = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
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
		final Grid grid = new Grid(container);
		
		grid.setSizeFull();
		//grid.setColumnOrder("firstName", "lastName");
		
		grid.setEditorEnabled(true);
		grid.setEditorSaveCaption("保存");
		grid.setEditorCancelCaption("取消");
		grid.setEditorErrorHandler(new Grid.EditorErrorHandler() {
			@Override
			public void commitError(CommitErrorEvent event) {
				event.setUserErrorMessage("红色编辑区域中的内容错误");
				
			}
		});
		//grid.setHeightByRows(15);
		grid.setHeightMode(HeightMode.ROW);
		// Handle selection in single-selection mode
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.getColumn("firstName").setHeaderCaption("姓名");
		grid.getColumn("id").setEditable(false);
		/*grid.getColumn("state").setRenderer(new AbstractRenderer<OrderState>(null) {
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return super.toString();
			}
		});*/
		grid.setColumnOrder("firstName","lastName");
		HeaderRow groupingHeader = grid.prependHeaderRow();
		groupingHeader.join(
		    groupingHeader.getCell("firstName"),
		    groupingHeader.getCell("lastName")).setText("Person");
	/*	groupingHeader.join(
		    groupingHeader.getCell("born"),
		    groupingHeader.getCell("died"),
		    groupingHeader.getCell("lived")).setText("Dates of Life");
		*/
		grid.addSelectionListener(e -> { // Java 8
			// Get the item of the selected row
			//BeanItem<Person> item = container.getItem(grid.getSelectedRow());

			// Use the item somehow
			//Notification.show("Selected " + item.getBean().getFirstName());
		});

		
		addComponent(grid);
		addComponent(new Button("分页", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				 Collection<Person> people = new ArrayList<>();
					for (int i = 100; i < 110; i++) {
						Person person = new Person();
						person.setId(i);
						person.setFirstName("liyang");
						person.setLastName(i+"");
						person.setState(OrderState.CANCEL);
						people.add(person);
					}
					System.out.println("======>"+OrderState.RECEIVED.name());
					// Have a container of some type to contain the data
					grid.setContainerDataSource(new BeanItemContainer<Person>(Person.class,people));
			}
		} ));
	}

}
