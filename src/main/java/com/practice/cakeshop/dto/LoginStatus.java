package com.practice.cakeshop.dto;

public class LoginStatus extends Status{
	
	private int customerId;
	private String email;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmailId(String email) {
		this.email = email;
	}
	
	

}
