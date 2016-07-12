package com.cooperay.cop_domo.vaadin.base.entry;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.cooperay.cop_domo.vaadin.base.ann.FormProperty;
import com.cooperay.cop_domo.vaadin.base.ann.HideProperty;

public class Account {

	@HideProperty
	private Long id;
	@NotNull
	@FormProperty(text = "姓名：")
	private String name;
	
	@NotNull
	@FormProperty(text = "生日：")
	private Date date;
	
	@NotNull
	@FormProperty(text = "状态：")
	private OrderState state;
	
	
	@FormProperty(text = "地址：")
	private String address;
	
	@FormProperty(text = "电话：")
	private String phone;
	
	@FormProperty(text = "年龄：")
	private Integer age;
	
	@FormProperty(text = "工作：")
	private String work;
	
	
	
	
	 public enum OrderState{
	        /** 已取消 */
	        CANCEL("已取消",1),
	        /** 待审核 */
	        WAITCONFIRM("待审核",2),
	        /** 等待付款 */
	        WAITPAYMENT("等待付款",3),
	        /** 正在配货 */
	        ADMEASUREPRODUCT ("正在配货",4),
	        /** 等待发货 */
	        WAITDELIVER("等待发货",5),
	        /** 已发货 */
	        DELIVERED ("已发货",6),
	        /** 已收货 */
	        RECEIVED ("已收货",7);
		 
		 	private String name;
		 	private Integer value;
	        private OrderState(String name,Integer value){
	        	this.name = name;
	        	this.value = value;
	        }
	        public String getName(){
	        	return name;
	        }
	        public String toString() {
	        	return getName();
	        }
	    }
	 
	 
	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}
	
	
	
	
	
	
	
}
