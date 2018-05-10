package com.hvcc.vo;

public class userDemo {
	private Integer ID;
	private String userName;
	private String password;
	private String email;
	private String phone;
	private String remarks;
	
	
	public userDemo(Integer iD, String userName, String password, String email, String phone, String remarks) {
		super();
		ID = iD;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.remarks = remarks;
	}
	public userDemo(String userName, String password, String remarks) {
		super();
		this.userName = userName;
		this.password = password;
		
		this.remarks = remarks;
	}
	public userDemo(Integer iD, String userName, String remarks) {
		super();
		ID = iD;
		this.userName = userName;
		this.remarks = remarks;
	}
	public userDemo(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public userDemo() {
		super();
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "userDemo [ID=" + ID + ", userName=" + userName + ", password=" + password + ", remarks=" + remarks
				+ "]";
	}
	
}
