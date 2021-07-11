package com.practice.cakeshop.service;

import java.util.List;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;

import com.practice.cakeshop.dto.CartItemDto;
import com.practice.cakeshop.dto.CategoryDto;
import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.dto.NotificationRequestDto;
import com.practice.cakeshop.dto.OrderDto;
import com.practice.cakeshop.dto.ProductDto;
import com.practice.cakeshop.entity.Cart;
import com.practice.cakeshop.entity.CartItem;
import com.practice.cakeshop.entity.Category;
import com.practice.cakeshop.entity.Customer;
import com.practice.cakeshop.entity.Expense;
import com.practice.cakeshop.entity.Order;
import com.practice.cakeshop.entity.OrderStatus;
import com.practice.cakeshop.entity.Product;

public interface CustomerService {
	
	public Customer register(Customer customer);
	public LoginStatus login(String emailId, String password);
	public Product addProduct(ProductDto product, HttpServletRequest request);
	public Category addCategory(CategoryDto category);
	public List<String> fetchCategoryNames();
	public List<Category> fetchProductsByCategoryName(String categoryName);
	public CartItem addToCart(CartItemDto cartDto);
	public Cart displayItemsOfCart(int customerId);
	public Order placeOrder(OrderDto orderDto);
	public List<Order> viewOrders(int customerId);
	public List<Order> viewOrdersByStatus(OrderStatus status);
	public Order changeOrderStatus(int orderId, OrderStatus status);
	public Expense addExpense(Expense expense);
	public List<Expense> viewAllExpense();
	public String sendPnsToDevice(NotificationRequestDto notificationRequestDto);

}
