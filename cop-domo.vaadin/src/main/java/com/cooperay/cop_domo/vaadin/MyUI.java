package com.cooperay.cop_domo.vaadin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

import com.cooperay.cop_domo.vaadin.Uitest.LoginView;
import com.cooperay.cop_domo.vaadin.Uitest.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.VaadinPropertyDescriptor;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("com.cooperay.cop_domo.vaadin.MyAppWidgetset")
@SpringUI
public class MyUI extends UI {
	

	@WebServlet(value = "/*", asyncSupported = true)
	public static class Servlet extends SpringVaadinServlet {

		@Override
		protected void service(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			super.service(request, response);
			System.out.println(request.getRequestURI());
		}

	}

	@WebListener
	public static class MyContextLoaderListener extends ContextLoaderListener {
	}

	@Configuration
	@EnableVaadin
	public static class MyConfiguration {
	}

	@Autowired
	private PorsonForm porsonForm;

	@Autowired
	private SpringViewProvider viewProvider;

	@Autowired
	private MenuBarView menuBarView;
	
	@Autowired
	private LoginFormView loginFormView;
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		/*Navigator navigator = new Navigator(this, this);
		navigator.addViewChangeListener(new ViewChangeListener() {
		
			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getViewName());
				if("main".equals(event.getViewName())){
					return true;  //;
				}
				return true;
			}
			
			@Override
			public void afterViewChange(ViewChangeEvent event) {
				// TODO Auto-generated method stub
			}
		});
		navigator.addProvider(viewProvider);*/
		// Navigate to start view
	//	navigator.navigateTo(MenuBarView.NAME);
		// ini();
		
		 setResponsive(true);
		 updateContent();
	        // Handle login and logout
	     /* setContent(menuBarView);
	      menuBarView.setNav();*/
	      //getUI().getNavigator().navigateTo();
	}

	public void updateContent(){
		 User user = (User) VaadinSession.getCurrent().getAttribute(
	                User.class.getName());
		 setContent(menuBarView);
		 menuBarView.setNav();
	       /* if (user != null) {
	            // Authenticated user
	            //getNavigator().navigateTo(getNavigator().getState());
	        } else {
	            setContent(loginFormView);
	        }*/
	}
	
	
	public void initUri() {
		getCurrent().getPage().addUriFragmentChangedListener(new UriFragmentChangedListener() {
			public void uriFragmentChanged(UriFragmentChangedEvent source) {
				enter(source.getUriFragment());
			}
		});
		// Read the initial URI fragment
		enter(getPage().getUriFragment());

	}

	public void enter(String fragment) {
		System.out.println(fragment);
	}

	/**
	 * @作者：李阳
	 * @时间：2016年6月22日 上午8:48:17
	 * @描述：备份示例 @参数：
	 */
	public void ini() {
		final VerticalLayout layout = new VerticalLayout();

		final TextField name = new TextField();
		name.setCaption("Type your name here:s");

		ComboBox combobox = new ComboBox("Select One");
		combobox.setInvalidAllowed(false);
		combobox.setNullSelectionAllowed(false);

		// Add some items and specify their item ID.
		// The item ID is by default used as item caption.
		combobox.addItems("Io", "Europa", "Ganymedes", "Callisto");
		combobox.addItem("tets");
		combobox.setValue("tets");

		// Handle selection change
		combobox.addValueChangeListener(event -> // Java 8
		layout.addComponent(new Label("Selected " + combobox.getValue())));

		Button button = new Button("Click Me");
		button.addClickListener(e -> {
			layout.addComponent(new Label("Thanks " + name.getValue() + ", it works!"));
		});

		Person person = new Person();
		// person.setSelect("Europa");
		BeanFieldGroup.bindFieldsUnbuffered(person, porsonForm);
		// Map<String,Object> map = new HashMap<String,Object>();
		// BeanFieldGroup.bindFieldsBuffered(map, porsonForm);
		porsonForm.addSubmitListener(new PorsonForm.FormSubmitListener<Button.ClickEvent>() {
			@Override
			public void submit(Button.ClickEvent e) {
				layout.addComponent(new Label("Persion name " + person.getFirstName() + " " + person.getLastName() + " "
						+ person.getBirthday()));
			}
		});

		layout.addComponents(name, button, combobox, porsonForm);
		layout.setMargin(true);
		layout.setSpacing(true);

		setContent(layout);
	}

	/*
	 * @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported =
	 * true)
	 * 
	 * @VaadinServletConfiguration(ui = MyUI.class, productionMode =
	 * false,widgetset="com.vaadin.spring.tutorial.AppWidgetSet") public static
	 * class MyUIServlet extends VaadinServlet { }
	 */
}
