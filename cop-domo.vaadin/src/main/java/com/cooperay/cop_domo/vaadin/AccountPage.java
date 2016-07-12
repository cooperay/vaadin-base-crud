package com.cooperay.cop_domo.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.cooperay.cop_domo.vaadin.base.presenter.AccountPresenter;
import com.cooperay.cop_domo.vaadin.base.service.AccountFacade;
import com.cooperay.cop_domo.vaadin.base.view.AccountView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = AccountPage.NAME)
public class AccountPage extends VerticalLayout implements View {

	public static final String NAME = "accountPage";
	
	public AccountPage() {
	}
	
	@Autowired
	private AccountFacade accountService;
	
	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("=========>account page");
		removeAllComponents();
		AccountView accountView = new AccountView();
		AccountPresenter presenter = new AccountPresenter(accountService, accountView);
		addComponent(presenter.getView());
		presenter.page(1, 1);
	}

}
