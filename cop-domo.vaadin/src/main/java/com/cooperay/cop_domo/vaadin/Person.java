package com.cooperay.cop_domo.vaadin;

import java.util.Date;

public class Person {

	private Integer id;
	private String firstName;
	private String lastName;
	private int email=1;
	private String remark;
	private Date birthday;
	private String select;
	private OrderState state;
	 public enum OrderState {
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getEmail() {
		return email;
	}
	public void setEmail(int email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
