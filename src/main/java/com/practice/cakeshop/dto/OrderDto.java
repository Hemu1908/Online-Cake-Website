package com.practice.cakeshop.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderDto {
	
	int orderId;
	int cartId;
	String shippingAddress;
	LocalDate orderedDateTime;
	LocalDate shippingDateTime;
	String deliveryTime;
	double amount;
	int customerId;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public LocalDate getOrderedDateTime() {
		return orderedDateTime;
	}
	public void setOrderedDateTime(LocalDate orderedDateTime) {
		this.orderedDateTime = orderedDateTime;
	}
	public LocalDate getShippingDateTime() {
		return shippingDateTime;
	}
	public void setShippingDateTime(LocalDate shippingDateTime) {
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
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	
	

}
