package com.practice.cakeshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.cakeshop.dto.CartItemDto;
import com.practice.cakeshop.dto.CategoryDto;
import com.practice.cakeshop.dto.LoginDto;
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
import com.practice.cakeshop.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	CustomerService custService;
	
	@PostMapping(value="/register")
	public Customer register(@RequestBody Customer customer ) {
		return custService.register(customer);
	}

	@PostMapping(value = "/login")
	   public LoginStatus loginCustomer(@RequestBody LoginDto loginData) {
		 return custService.login(loginData.getEmail(), loginData.getPassword());
	 }
	
	@PostMapping(value = "/addProduct")
	public Product addProduct(@ModelAttribute ProductDto product, HttpServletRequest request) {
		try {
			return custService.addProduct(product, request);
		}catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping(value = "/addCategory")
	public Category addCategory(@RequestBody CategoryDto category) {
		try {
			return custService.addCategory(category);
		}catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping(value = "/addToCart")
	public CartItem addToCart(@RequestBody CartItemDto cartDto) {
		try {
			return custService.addToCart(cartDto);
		}catch(Exception e) {
			return null;
		}
	}
	
	@GetMapping(value = "/fetchCategory")
	public List<String> fetchCategoryNames() {
		return custService.fetchCategoryNames();
	}
	
	@GetMapping(value = "/fetchProducts")
	public List<Category> fetchProductsByCategoryName(@RequestParam String categoryName) {
		return custService.fetchProductsByCategoryName(categoryName);
	}
	
	@GetMapping(value = "/displayCartItems")
	public Cart displayCartItems(@RequestParam int customerId) {
		return custService.displayItemsOfCart(customerId);
	}
	
	@PostMapping(value = "/placeOrder")
	public Order placeOrder(@RequestBody OrderDto orderDto) {
		try {
			return custService.placeOrder(orderDto);
		}catch(Exception e) {
			return null;
		}
	}
	
	@GetMapping(value = "/viewOrders")
	public List<Order> viewOrders(@RequestParam int customerId) {
		return custService.viewOrders(customerId);
	}
	
	@GetMapping(value = "/viewOrdersByStatus")
	public List<Order> viewOrderByStatus(@RequestParam OrderStatus status) {
		return custService.viewOrdersByStatus(status);
	}
	
	@PutMapping(value = "changeOrderStatus")
	public Order changeOrderStatus(@RequestParam int orderId, @RequestParam OrderStatus status) {
		return custService.changeOrderStatus(orderId, status);
	}
	
	@PostMapping(value="addExpense")
	public Expense addExpense(@RequestBody Expense expense) {
		return custService.addExpense(expense);
	}
	
	@GetMapping(value = "viewAllExpense")
	public List<Expense> viewAllExpense() {
		return custService.viewAllExpense();
	}
	
	@PostMapping(value = "token")
    public String sendPnsToDevice(@RequestBody NotificationRequestDto notificationRequestDto) {
        return custService.sendPnsToDevice(notificationRequestDto);
    }

}


