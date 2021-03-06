package com.practice.cakeshop.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.practice.cakeshop.dto.CartItemDto;
import com.practice.cakeshop.dto.CategoryDto;
import com.practice.cakeshop.dto.LoginStatus;
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

public interface CustomerRepository {
	
	public Customer register(Customer customer);
	public LoginStatus login(String email, String password);
	public Customer findCustomerById(int customerId);
	public boolean isProductPresent(String name);
	public Product addProduct(ProductDto productDto, HttpServletRequest request);
	public boolean isCategoryPresent(String name);
	public Category addCategory(CategoryDto categoryDto);
	public Category findCategoryByName(String name);
	public Product findProductByProductId(int productId);
	public List<String> fetchCategoryNames();
	public List<Category> fetchProductsByCategoryName(String categoryName);
	public CartItem addToCart(CartItemDto cartDto);
	public Cart displayAllItemsOfCart(int customerId);
	public Order placeOrder(OrderDto orderDto);
	public CartItem findCartItemById(int cartItemId);
	public List<Order> viewOrders(int customerId);
	public Cart findCartById(int cartId);
	public int deleteCart(int cartId);
	public int deleteCartItem(int cartItemId);
	public Order findOrdersByCustomerId(int customerId);
	public Order findOrderById(int orderId);
	public List<Order> viewOrdersByStatus(OrderStatus status);
	public Order changeOrderStatus(int orderId, OrderStatus status);
	public Expense addExpense(Expense expense);
	public List<Expense> viewAllExpense();
}
