package com.cooperay.cop_domo.vaadin.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooperay.cop_domo.vaadin.base.entry.Account;
import com.cooperay.cop_domo.vaadin.base.entry.Group;
import com.cooperay.cop_domo.vaadin.base.entry.Account.OrderState;

@Service("groupFacade")
public class GroupFacade implements BaseFacadeInterface<Group> {

	@Override
	public void add(Group t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Group> page(Integer page, Integer row) {
		List<Group> list = new ArrayList<>();
		for(int i = 0 ; i <15  ; i++){
			Group account = new Group();
			account.setName("test"+i);
			account.setRemark("remark"+i);
			list.add(account);
		}
		return list;
	}

}
