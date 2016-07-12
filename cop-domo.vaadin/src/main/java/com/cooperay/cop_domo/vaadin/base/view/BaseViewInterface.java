package com.cooperay.cop_domo.vaadin.base.view;

import java.util.List;

public interface BaseViewInterface<T> {

	void addListener(BaseViewLinster<T> linster);

	void setPage(List<T> list);
	
}
