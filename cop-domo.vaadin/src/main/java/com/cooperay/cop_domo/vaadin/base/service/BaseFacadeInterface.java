package com.cooperay.cop_domo.vaadin.base.service;

import java.util.List;

public interface BaseFacadeInterface <T>{

	void add(T t);
	
	List<T> page(Integer page,Integer row);
	
}
