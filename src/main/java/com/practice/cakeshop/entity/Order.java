package com.practice.cakeshop.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shop_order")
public class Order {
	
	@Id
	@SequenceGenerator(name="order_seq", initialValue=101, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="order_seq")
	@Column(name = "order_id")
	int orderId;
	
	String shippingAddress;
	
	LocalDate orderedDateTime;
	String timeslot;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate shippingDate;

	double amount;
	
	OrderStatus orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	Customer customer;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	List<OrderItem> orderItem;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public LocalDate getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	
}
