package com.practice.cakeshop.dto;

import java.time.LocalDateTime;

public class OrderDto {
	
	int orderId;
	int cartItemId;
	String shippingAddress;
	LocalDateTime orderedDateTime;
	LocalDateTime shippingDateTime;
	double amount;
	int customerId;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public LocalDateTime getOrderedDateTime() {
		return orderedDateTime;
	}
	public void setOrderedDateTime(LocalDateTime orderedDateTime) {
		this.orderedDateTime = orderedDateTime;
	}
	public LocalDateTime getShippingDateTime() {
		return shippingDateTime;
	}
	public void setShippingDateTime(LocalDateTime shippingDateTime) {
		this.shippingDateTime = shippingDateTime;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	

}
