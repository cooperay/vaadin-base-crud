package com.cooperay.cop_domo.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.cooperay.cop_domo.vaadin.base.presenter.AccountPresenter;
import com.cooperay.cop_domo.vaadin.base.presenter.GroupPresenter;
import com.cooperay.cop_domo.vaadin.base.service.AccountFacade;
import com.cooperay.cop_domo.vaadin.base.service.GroupFacade;
import com.cooperay.cop_domo.vaadin.base.view.AccountView;
import com.cooperay.cop_domo.vaadin.base.view.GroupView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = GroupPage.NAME)
public class GroupPage extends VerticalLayout implements View {

	public static final String NAME = "groupPage";
	
	public GroupPage() {
		setMargin(true);
	}
	
	@Autowired
	private GroupFacade groupFacade;
	
	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		GroupView groupView = new GroupView();
		GroupPresenter presenter = new GroupPresenter(groupFacade, groupView);
		addComponent(presenter.getView());
		presenter.page(1, 1);
	}

}
