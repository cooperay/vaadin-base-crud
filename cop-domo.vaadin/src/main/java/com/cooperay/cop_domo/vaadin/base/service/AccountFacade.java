package com.cooperay.cop_domo.vaadin.base.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooperay.cop_domo.vaadin.base.entry.Account;
import com.cooperay.cop_domo.vaadin.base.entry.Account.OrderState;

@Service("accountService")
public class AccountFacade implements BaseFacadeInterface<Account> {

	@Override
	public void add(Account t) {
		System.out.println(t.getName());
	}

	@Override
	public List<Account> page(Integer page, Integer row) {
		List<Account> list = new ArrayList<>();
		for(int i = 0 ; i <10  ; i++){
			Account account = new Account();
			account.setName("test"+i);
			account.setDate(new Date());
			account.setState(OrderState.ADMEASUREPRODUCT);
			list.add(account);
		}
		return list;
	}

		

}
