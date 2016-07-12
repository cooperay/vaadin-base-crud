package com.cooperay.cop_domo.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent("menuBarView")
@UIScope
public class MenuBarView extends VerticalLayout implements View{

	public static final String NAME = "main";
	
	public MenuBarView() {
		init();
	}
	
	@Autowired
	private SpringViewProvider springViewProvider;
	
	private Navigator navigator = null;
	
	public class MenuItemClick implements Command{
		private String menuId;
		public MenuItemClick(String menuId) {
			this.menuId = menuId;
		}
		@Override
		public void menuSelected(MenuItem selectedItem) {
			if(this.menuId !=null && !"".equals(this.menuId))
			UI.getCurrent().getNavigator().navigateTo(menuId);
		}
	}
	
	Panel contentArea;
	
	public void init() {
		//setSpacing(true);
		addComponent(buildHead2());
		MenuBar menuBar = new MenuBar();
		
		addComponent(menuBar);
		
		MenuItem baseItem = menuBar.addItem("基础设置", null);
		baseItem.addItem("用户管理", FontAwesome.USER,new MenuItemClick(BeanItemView.NAME));
		baseItem.addItem("表格测试", FontAwesome.USER,new MenuItemClick(TableView.NAME));
		baseItem.addItem("Grid测试", FontAwesome.USER,new MenuItemClick(GridView.NAME));
		baseItem.addItem("MVP测试", FontAwesome.USER,new MenuItemClick(MvpView.NAME));
		baseItem.addItem("BeanItem表单测试", FontAwesome.USER,new MenuItemClick(BeanItemView.NAME));
		baseItem.addItem("自定义组件测试", FontAwesome.USER,new MenuItemClick(CustomComponentView.NAME));
		baseItem.addItem("Base测试", FontAwesome.USER,new MenuItemClick(AccountPage.NAME));
		baseItem.addItem("BaseGroup测试", FontAwesome.USER,new MenuItemClick(GroupPage.NAME));
		menuBar.addItem("图标测试", FontAwesome.AMAZON,new MenuItemClick(PanelView.NAME));
		menuBar.addItem("登陆窗测试",new MenuItemClick(LoginFormView.NAME));
		menuBar.addItem("退出系统",new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				VaadinSession.getCurrent().setAttribute(User.class.getName(),null);
				/*MyUI myUI = (MyUI)getUI();
				myUI.updateContent();*/
				Page.getCurrent().reload();
			}
		});
		menuBar.setSizeFull();
		menuBar.setHeightUndefined();
		
		 contentArea = new Panel("");
         contentArea.addStyleName(ValoTheme.PANEL_BORDERLESS);
         contentArea.setSizeFull();
         addComponent(contentArea);
         setExpandRatio(contentArea, 1.0f);
	}
	
	public Component getPanel(){
		return contentArea;
	}
	
	
	public void setNav(){
		navigator = new Navigator(UI.getCurrent(),contentArea);
		navigator.addViewChangeListener(new ViewChangeListener() {
			
			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public void afterViewChange(ViewChangeEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		navigator.addProvider(springViewProvider);
		navigator.setErrorView(ErrorView.class);
		if(navigator.getState()==""){
			navigator.navigateTo(WelcomeView.NAME);
		}else{
			navigator.navigateTo(navigator.getState());
		}
		//navigator.navigateTo(WelcomeView.NAME);
	}
	
	private Component buildHead(){
		HorizontalLayout layout = new HorizontalLayout();
		layout.setHeight(60,Unit.PIXELS);
		layout.addStyleName("main-head");
		
		Label systemName = new Label("信息管理系统");
		systemName.setIcon(FontAwesome.USER);
		systemName.addStyleName(ValoTheme.LABEL_H2);
	    systemName.addStyleName(ValoTheme.LABEL_NO_MARGIN);
	    
	    ThemeResource logo = new ThemeResource("../../icon/logo.png");
	    Image logoImage = new Image(null,logo);
	    logoImage.setHeight(60,Unit.PIXELS);
	    layout.addComponent(logoImage);
		
	   /* layout.addComponent(systemName);
	    layout.setComponentAlignment(systemName,Alignment.MIDDLE_RIGHT);*/
		return layout;
	}
	
	private Component buildHead2(){
		GridLayout gridLayout = new GridLayout(3,1);
		gridLayout.setSizeFull();
		gridLayout.setHeight(60,Unit.PIXELS);
		gridLayout.addStyleName("main-head");
		
		ThemeResource logo = new ThemeResource("../../icon/logo.png");
		Image logoImage = new Image(null,logo);
		logoImage.setHeight(60,Unit.PIXELS);
		gridLayout.addComponent(logoImage,0,0);
		
		Label systemName = new Label("信息管理系统");
		systemName.setSizeUndefined();
		systemName.setIcon(FontAwesome.USER);
		systemName.addStyleName(ValoTheme.LABEL_H2);
	    systemName.addStyleName(ValoTheme.LABEL_NO_MARGIN);
	    //gridLayout.addComponent(systemName,2,0);
	    //gridLayout.setComponentAlignment(systemName, Alignment.MIDDLE_RIGHT);
	    
	    Component userSetting = buildUserMenu();
	    gridLayout.addComponent(userSetting,2,0);
	    gridLayout.setComponentAlignment(userSetting, Alignment.MIDDLE_RIGHT);
	    
		
		return gridLayout;
	}

	private Component buildUserMenu() {
		MenuItem settingsItem;
        final MenuBar settings = new MenuBar();
        settings.setHeight(40,Unit.PIXELS);
        settings.addStyleName("user-menu");
        settingsItem = settings.addItem("", new ThemeResource(
                "img/profile-pic-300px.jpg"), null);
        settingsItem.addItem("个人设置", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
            }
        });
        settingsItem.addItem("系统设置", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
            }
        });
        settingsItem.addSeparator();
        settingsItem.addItem("登出", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
            }
        });
        return settings;
    }
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		 /*if (event.getParameters() == null
	                || event.getParameters().isEmpty()) {
	                contentArea.setContent(
	                    new Label("Nothing to see here, " +
	                              "just pass along."));
	                return;
	            } */
	}
	

}
