package com.cooperay.cop_domo.vaadin;

import com.vaadin.ui.Grid.AbstractRenderer;
import com.vaadin.ui.renderers.NumberRenderer;

import elemental.json.JsonValue;

public class EnumRender extends AbstractRenderer<Enum> {

	protected EnumRender(Class<Enum> presentationType) {
		super(presentationType);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public JsonValue encode(Enum value) {
		
		return super.encode(value);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
