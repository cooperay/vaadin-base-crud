package com.cooperay.cop_domo.vaadin;

import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;


@SpringView(name = TableView.NAME)
public class TableView extends VerticalLayout implements View {

	public static final String NAME = "system/table/tableView";
	
	public TableView() {
		setMargin(true);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addComponent(buildSeachForm());
		/*setCaptionAsHtml(true);
		setCaption("<h3>用户信息</h3>");*/
		Table table = new Table();
		
		table.setSizeFull();
		// Define two columns for the built-in container
		table.addContainerProperty("Name", String.class, null, "名称",FontAwesome.USER,null);
		table.addContainerProperty("Mag",  Float.class, null);
		table.addContainerProperty("detail", Button.class, null);

		// Add a row the hard way
		Object newItemId = table.addItem();
		Item row1 = table.getItem(newItemId);
		row1.getItemProperty("Name").setValue("Sirius");
		row1.getItemProperty("Mag").setValue(-1.46f);
		
		Button button = new Button("详细");
		button.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				showDetail();
			}
		});
		button.addStyleName("link");
		row1.getItemProperty("detail").setValue(button);

		// Add a few other rows using shorthand addItem()
		
		for(int i = 1; i<100;i++){
			table.addItem(new Object[]{"item"+1, -0.72f,new Button("详细信息")}, i+1);
		}

		// Show exactly the currently contained rows (items)
		table.setPageLength(15);
		addComponent(table);
	}
	
	public Component buildSeachForm(){
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addComponent(new Label("<h3>基础设置->表格测试</h3>",ContentMode.HTML));
		ComboBox select = new ComboBox();
		select.setInputPrompt("科目");
		select.addItem("Io");
    	select.setItemCaption("Io","选择的直为Io");
    	select.addItem("Europa");
    	select.addItem("Ganymedes");
    	select.addItem("Callisto");
    	TextField textField = new TextField();
    	textField.setInputPrompt("用户名");
		layout.addComponent(textField);
		layout.setComponentAlignment(textField,Alignment.MIDDLE_CENTER);
		layout.addComponent(select);
		layout.setComponentAlignment(select,Alignment.MIDDLE_CENTER);
		Button button = new Button(FontAwesome.SEARCH);
		button.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		layout.addComponent(button);
		layout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
		Button button2 = new Button(FontAwesome.REFRESH);
		layout.addComponent(button2);
		layout.setComponentAlignment(button2, Alignment.MIDDLE_CENTER);
		return layout;
	}
	
	
	public void showDetail(){
		 Window subWindow = new Window("用户详细信息");
		// subWindow.setSizeFull();
		 //subWindow.setClosable(false);
		 //subWindow.setResizable(false);
	        VerticalLayout subContent = new VerticalLayout();
	        subContent.setMargin(true);
	        subWindow.setContent(subContent);
	        subWindow.setModal(true);
	        subWindow.setWidth(900,Unit.PIXELS);
	        PorsonForm porsonForm = new PorsonForm();
	        // Put some components in it
	        subContent.addComponent(porsonForm);
	        // Center it in the browser window
	        subWindow.center();
	        // Open it in the UI
	        getUI().addWindow(subWindow);
	}

}
