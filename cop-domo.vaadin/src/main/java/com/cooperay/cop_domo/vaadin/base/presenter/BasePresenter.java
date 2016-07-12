package com.cooperay.cop_domo.vaadin.base.presenter;

import java.util.List;

import com.cooperay.cop_domo.vaadin.base.service.BaseFacadeInterface;
import com.cooperay.cop_domo.vaadin.base.view.BaseView;
import com.cooperay.cop_domo.vaadin.base.view.BaseViewLinster;

public class BasePresenter<T> implements BaseViewLinster<T> {

	private BaseFacadeInterface<T> service;
	
	private BaseView<T> view;
	
	public BasePresenter(BaseFacadeInterface<T> service,BaseView<T> view) {
		this.service = service;
		this.view = view;
		view.addListener(this);
	}

	public BaseView<T> getView() {
		return view;
	}


	@Override
	public void add(T entry) {
		service.add(entry);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(T entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void page(Integer page, Integer rows) {
		List<T> list = service.page(1,2);
		view.setPage(list);
	}
	
}
