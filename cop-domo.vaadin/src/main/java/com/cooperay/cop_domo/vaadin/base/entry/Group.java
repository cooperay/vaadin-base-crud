package com.cooperay.cop_domo.vaadin.base.entry;

import com.cooperay.cop_domo.vaadin.base.ann.FormProperty;
import com.cooperay.cop_domo.vaadin.base.ann.HideProperty;

public class Group {
	@HideProperty
	private Integer id;
	
	@FormProperty(text = "名称：")
	private String name;
	
	@FormProperty(text = "备注")
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
