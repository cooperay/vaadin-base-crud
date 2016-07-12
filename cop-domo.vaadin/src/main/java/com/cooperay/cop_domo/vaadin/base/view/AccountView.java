package com.cooperay.cop_domo.vaadin.base.view;

import com.cooperay.cop_domo.vaadin.base.entry.Account;
import com.vaadin.ui.Component;

public class AccountView extends BaseView<Account> {
	public AccountView() {
		super(new Account(),"基础设置->Base设置");
	}

	private static final long serialVersionUID = 1L;


}
