package com.cooperay.cop_domo.vaadin.base.view;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cooperay.cop_domo.vaadin.base.ann.FormProperty;
import com.cooperay.cop_domo.vaadin.base.ann.HideProperty;
import com.cooperay.cop_domo.vaadin.component.ConfimWindow;
import com.cooperay.cop_domo.vaadin.component.PageBar;
import com.cooperay.cop_domo.vaadin.component.PageBar.PageBarEvent;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.SelectionEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.CommitErrorEvent;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public abstract class BaseView<T> extends VerticalLayout implements BaseViewInterface<T> {
	final String STATE_SAVE = "save";
	public static final String STATE_UPDATE = "update";
	private static final long serialVersionUID = 1L;
	private static final Integer FIELD_COL_SIZE = 10;
	private Integer fieldCount;
	private BaseViewLinster<T> linster;
	private T entry;  
	private T temp;
	private List<T> selectedList;  //选中列表
	
	
	/**初示参数**/
	private Integer rows = 15;
	private boolean editorEnabled = true;
	private List<T> list;  //初始列表
	
	private Window editWindow;  //编辑窗口
	private Grid grid;
	public BaseView(T t,String caption) {
		temp = t;
		entry = t;
		init(caption);
	}

	@Override
	public void setPage(List<T> list) {
		grid.setContainerDataSource(new BeanItemContainer(entry.getClass(),list));
		Integer row = grid.getContainerDataSource().size();
		if(row>0){
			grid.setHeightByRows(row);
		}
	}

	protected void init(String caption) {
		Panel panel = new Panel();
		VerticalLayout panelContent = new VerticalLayout();
		panel.setContent(panelContent);
		panel.setSizeFull();
		panelContent.setMargin(true);
		panelContent.setSpacing(true);
		panelContent.addComponent(createToolBar());
		panelContent.addComponent(createGrid());
		panelContent.addComponent(createPageBar());
		addComponent(panel);
	}

	/**
	 * @作者：李阳
	 * @时间：2016年6月22日 上午8:48:17
	 * @描述：创建分页组件
	 * @参数：@return
	 */
	protected Component createPageBar(){
		PageBar pageBar = new PageBar(1000, 17);
		pageBar.setPageBarEventLinster(new PageBar.PageBarEventLinster() {
			@Override
			public void pageChangeEvent(PageBarEvent pageBarEvent) {
				System.out.println(pageBarEvent.getPageButton().getPage() + "------- rows "+pageBarEvent.getRows());
			}
		});
		return pageBar;
	}
	
	/**
	 * @作者：李阳
	 * @时间：2016年6月22日 上午8:48:17
	 * @描述：创建编辑窗口 用户新建/编辑
	 * @参数：@param type  ‘save’为新建   ‘update’ 为编辑
	 * @参数：@return
	 */
	protected Window createEditWindow(String type){
		editWindow = new Window("新增/编辑窗口");
		editWindow.setContent(createForm(type));
		editWindow.setModal(true);
		editWindow.setWidth(500,Unit.PIXELS);
		editWindow.center();
       return editWindow;
	}
	
	/**
	 * @作者：李阳
	 * @时间：2016年6月22日 上午8:48:17
	 * @描述：创建工具条
	 * @参数：@return
	 */
	protected Component createToolBar(){
		HorizontalLayout layout = new HorizontalLayout();
		Label title = new Label("编辑区域->");
		layout.addComponent(title);
		
		//创建操作按钮
		Button add  = new Button("新增");
		add.addStyleName(ValoTheme.BUTTON_TINY);
		add.setIcon(FontAwesome.PLUS);
		add.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				entry = temp;
				editWindow = createEditWindow(STATE_SAVE);
		        getUI().addWindow(editWindow);
			}
		});
		
		Button update  = new Button("修改");
		update.setIcon(FontAwesome.EDIT);
		update.addStyleName(ValoTheme.BUTTON_TINY);
		update.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(entry.equals(temp)){
					Notification.show("未选中内容");
					return;
				}
				editWindow = createEditWindow(STATE_UPDATE);
		        getUI().addWindow(editWindow);
			}
		});
		
		Button del  = new Button("删除");
		del.addStyleName(ValoTheme.BUTTON_TINY);
		del.setIcon(FontAwesome.REMOVE);
		del.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(selectedList == null || selectedList.size()<=0){
					Notification.show("未选中内容");
					return;
				}
				ConfimWindow confimWindow = new ConfimWindow("警告");
				confimWindow.setConfimEventLinster(new ConfimWindow.ConfimEventLinster() {
					@Override
					public void confim(ConfimWindow confimWindow) {
						Notification.show("删除记录");
						confimWindow.close();
					}
					@Override
					public void cancel(ConfimWindow confimWindow) {
						confimWindow.close();
					}
				});
				getUI().addWindow(confimWindow);	
			}
		});
		layout.addComponent(add);
		layout.addComponent(update);
		layout.addComponent(del);
		
		//创建搜索区域
		TextField textField = new TextField();
		textField.addStyleName(ValoTheme.TEXTFIELD_TINY);
		textField.setInputPrompt("mingcheng");
		layout.addComponent(textField);
		
		return layout;
	}
	
	
	/**
	 * @作者：李阳
	 * @时间：2016年6月22日 上午8:48:17
	 * @描述：创建form用来修改和新增
	 * @参数：@return
	 */
	public Component createForm(String type){
		FormLayout layout = new FormLayout();
		layout.setMargin(true);
		if(entry==null){
			return null;
		}
		Field[] fields= entry.getClass().getDeclaredFields();
		fieldCount = fields.length;
		final BeanFieldGroup<T> binder =  new BeanFieldGroup(entry.getClass());
		binder.setItemDataSource(entry);
		for (Field field : fields) {
			FormProperty formProperty = field.getAnnotation(FormProperty.class);
			if (formProperty == null) {
				continue;
			}
			String caption = field.getName();
			caption = formProperty.text();
			com.vaadin.ui.Field formField = null;
			if(Enum.class.isAssignableFrom(field.getType())){
				formField = binder.buildAndBind(caption,field.getName(),ComboBox.class);
			}else {
				formField = binder.buildAndBind(caption,field.getName());
			}
			formField.addValidator(new BeanValidator(entry.getClass(), field.getName()));
			if(formField instanceof TextField){
				((TextField)formField).setNullRepresentation("");
				((TextField)formField).setNullSettingAllowed(true);
			}
			layout.addComponent(formField);
		}
		layout.addComponent(createFormButton(type,binder));
		return layout;
	}
	
	/**
	 * @作者：李阳
	 * @时间：2016年6月22日 上午8:48:17
	 * @描述：创建通用表格
	 * @参数：@return
	 */
	private Component createGrid(){
		// Have a container of some type to contain the data
		BeanItemContainer<T> container = new BeanItemContainer(entry.getClass(),list);
		// Create a grid bound to the container
		grid = new Grid(container);
		grid.setSizeFull();
		grid.setEditorEnabled(editorEnabled);
		grid.setEditorSaveCaption("保存");
		grid.setEditorCancelCaption("取消");
		grid.setEditorErrorHandler(new Grid.EditorErrorHandler() {
			@Override
			public void commitError(CommitErrorEvent event) {
				event.setUserErrorMessage("红色编辑区域中的内容错误");
			}
		});
		grid.setHeightByRows(rows);
		grid.setHeightMode(HeightMode.ROW);
		
		// Handle selection in single-selection mode
		grid.setSelectionMode(SelectionMode.MULTI);
		Field[] fields= entry.getClass().getDeclaredFields();
		for (Field field : fields) {
			HideProperty hideProperty = field.getAnnotation(HideProperty.class);
			if(hideProperty!=null){
				grid.removeColumn(field.getName());
				continue;
			}
			FormProperty formProperty = field.getAnnotation(FormProperty.class);
			String caption = field.getName();
			if(formProperty!=null){
				caption = formProperty.text();
			}
			Column column = grid.getColumn(field.getName());
			column.setHeaderCaption(caption);
			com.vaadin.ui.Field rowEditField =  column.getEditorField();
			rowEditField.addValidator(new BeanValidator(entry.getClass(), field.getName()));
			if(rowEditField instanceof TextField){
				((TextField)rowEditField).setNullRepresentation("");
				((TextField)rowEditField).setNullSettingAllowed(true);
				column.setEditorField(rowEditField);
			}
		}
		
		grid.addSelectionListener(new SelectionEvent.SelectionListener() {
			@Override
			public void select(SelectionEvent event) {
				selectedList = new ArrayList<>();
				Set<Object> set = event.getSelected();
				Iterator<Object> iterator = set.iterator();
				while (iterator.hasNext()) {
					T object = (T) iterator.next();
					selectedList.add(object);
				}
				if(selectedList.size()>0){
					entry = selectedList.get(0);
				}else{
					entry = temp;
				}
			}
		});
		return grid;
	}

	/**
	 * @作者：李阳
	 * @时间：2016年6月22日 上午8:48:17
	 * @描述：创建form操作按钮
	 * @参数：@return
	 */
	
	private Component createFormButton(String type,BeanFieldGroup beanFieldGroup){
		HorizontalLayout layout = new HorizontalLayout();
		Button submit = null;
		Button canel = new Button("取消");
		if(STATE_SAVE.equals(type)){
			submit = new Button("保存");
			submit.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;
				@Override
				public void buttonClick(ClickEvent event) {
					try {
						beanFieldGroup.commit();
					} catch (CommitException e) {
						e.printStackTrace();
					}
					linster.add(entry);
				}
			});
		}else if(STATE_UPDATE.equals(type)){
			submit = new Button("修改");
			submit.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;
				@Override
				public void buttonClick(ClickEvent event) {
					
				}
			});
		}
		canel.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 2723537829650691217L;

			@Override
			public void buttonClick(ClickEvent event) {
				if(editWindow != null){
					editWindow.close();
				}
			}
		});
		layout.setSpacing(true);
		layout.addComponent(submit);
		layout.addComponent(canel);
		return layout;
	}
	
	@Override
	public void addListener(BaseViewLinster<T> linster) {
		this.linster = linster;
	}
	
	protected void setPageRow(Integer rows){
		this.rows = rows;
	}
	
}
