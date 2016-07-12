package com.cooperay.cop_domo.vaadin;

import org.springframework.stereotype.Component;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

@Component("porsonForm")
public class PorsonForm extends FormLayout {
	FormSubmitListener<Button.ClickEvent> listener =null;
	String string = "12321321";
	// Define the input fields for data.
	TextField firstName = new TextField("姓：");
	TextField lastName = new TextField("名：");
	TextField email = new TextField("邮箱：");
	DateField birthday = new DateField("生日：");
	
	ComboBox select = new ComboBox("科目");
    
	Button button = new Button("保存");
	
	Button button2 = new Button("取消");
    public PorsonForm() {
    	HorizontalLayout verticalLayout = new HorizontalLayout();
    	verticalLayout.addComponents(button,new Label("&nbsp;&nbsp;", com.vaadin.shared.ui.label.ContentMode.HTML),button2);
        addComponents(firstName, lastName, email, birthday,select,verticalLayout);
        init();
    }
    
    public void init(){
    	select.addItem("Io");
    	
    	select.setItemCaption("Io","选择的直为Io");
    	
    	select.addItem("Europa");
    	select.addItem("Ganymedes");
    	select.addItem("Callisto");
    	// User may not select a "null" item
    	//select.setNullSelectionAllowed(false);
    	//button.setComponentError(new UserError("错误信息测试"));
    	//getUI().getReconnectDialogConfiguration().setDialogText(dialogText);
    	//button.setIcon(new ThemeResource("../icon/folder.png"));
    	button.setIcon(FontAwesome.USER);
    	button.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		 button.addClickListener( e -> {
			 if(listener!=null){
				 listener.submit(e);
			 }
		 });
    }

    public void addSubmitListener(FormSubmitListener<Button.ClickEvent> formSubmitListener){
    	listener = formSubmitListener;
    }
    
    public interface FormSubmitListener<T>{
    	public void submit(T entry);
    }

	public TextField getFirstName() {
		return firstName;
	}
	
}
