package com.cooperay.cop_domo.vaadin.component;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class ConfimWindow extends Window {
	
	public interface ConfimEventLinster{
		void confim(ConfimWindow confimWindow);
		void cancel(ConfimWindow confimWindow);
	}
	
	public class ConfimEvent{
		private ConfimWindow confimWindow;
		public ConfimEvent(ConfimWindow confimWindow) {
			this.confimWindow = confimWindow;
		}
		public ConfimWindow getConfimWindow() {
			return confimWindow;
		}
		public void setConfimWindow(ConfimWindow confimWindow) {
			this.confimWindow = confimWindow;
		}
	}
	
	private ConfimEventLinster confimEventLinster;
	
	public ConfimWindow(String title) {
		super(title);
		init();
	}
	
	private void init(){
		setWidth(250,Unit.PIXELS);
		setModal(true);
		center();
		setResizable(false);
		VerticalLayout layout = new VerticalLayout();
		HorizontalLayout buttonLayout = new HorizontalLayout();
		Label label = new Label("确认删除？");

		Button confim = new Button("确定");
		Button cancel = new Button("取消");

		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(label);
		layout.addComponent(buttonLayout);
		
		buttonLayout.setSpacing(true);
		
		confim.addStyleName(ValoTheme.BUTTON_DANGER);
		
		confim.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(confimEventLinster!=null){
					confimEventLinster.confim(ConfimWindow.this);
				}
			}
		});
		
		cancel.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(confimEventLinster!=null){
					confimEventLinster.cancel(ConfimWindow.this);
				}
			}
		});
		
		
		buttonLayout.addComponents(confim,cancel);
		setContent(layout);
	}

	public ConfimEventLinster getConfimEventLinster() {
		return confimEventLinster;
	}

	public void setConfimEventLinster(ConfimEventLinster confimEventLinster) {
		this.confimEventLinster = confimEventLinster;
	}
	
	
}
