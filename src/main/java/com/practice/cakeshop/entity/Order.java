package com.practice.cakeshop.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "shop_order")
public class Order {
	
	@Id
	@SequenceGenerator(name="order_seq", initialValue=101, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="order_seq")
	@Column(name = "order_id")
	int orderId;
	
	@OneToOne
	@JoinColumn(name = "cartId")
	Cart cart;
	
	String shippingAddress;
	
	LocalDate orderedDateTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate shippingDateTime;

	double amount;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	Customer customer;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
