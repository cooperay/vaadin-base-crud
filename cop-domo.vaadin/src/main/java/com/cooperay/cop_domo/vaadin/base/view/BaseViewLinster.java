package com.cooperay.cop_domo.vaadin.base.view;

public interface BaseViewLinster<T> {
	void add(T entry);
	
	void delete(Long id);
	
	void update(T entry);
	
	void page(Integer page,Integer rows);
}
