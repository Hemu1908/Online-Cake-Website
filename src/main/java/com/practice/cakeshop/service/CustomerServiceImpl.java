package com.practice.cakeshop.service;

import java.util.List;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.practice.cakeshop.dto.CartItemDto;
import com.practice.cakeshop.dto.CategoryDto;
import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.dto.OrderDto;
import com.practice.cakeshop.dto.ProductDto;
import com.practice.cakeshop.entity.Cart;
import com.practice.cakeshop.entity.CartItem;
import com.practice.cakeshop.entity.Category;
import com.practice.cakeshop.entity.Customer;
import com.practice.cakeshop.entity.Order;
import com.practice.cakeshop.entity.Product;
import com.practice.cakeshop.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository custRepo;

	@Override
	public Customer register(Customer customer) {
		// TODO Auto-generated method stub
		return custRepo.register(customer);
	}

	@Override
	public LoginStatus login(String emailId, String password) {
		// TODO Auto-generated method stub
		return custRepo.login(emailId, password);
	}

	@Override
	public Product addProduct(ProductDto product) {
		return custRepo.addProduct(product);
		
	}

	@Override
	public Category addCategory(CategoryDto category) {
		return custRepo.addCategory(category);
	}

	@Override
	public List<String> fetchCategoryNames() {
		// TODO Auto-generated method stub
		return custRepo.fetchCategoryNames();
	}

	@Override
	public List<Category> fetchProductsByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return custRepo.fetchProductsByCategoryName(categoryName);
	}

	@Override
	public CartItem addToCart(CartItemDto cartDto) {
		return custRepo.addToCart(cartDto);
	}

	@Override
	public Cart displayItemsOfCart(int customerId) {
		// TODO Auto-generated method stub
		return custRepo.displayAllItemsOfCart(customerId);
	}

	@Override
	public Order placeOrder(OrderDto orderDto) {
		// TODO Auto-generated method stub
		return custRepo.placeOrder(orderDto);
	}

	@Override
	public List<Order> viewOrders(int orderId) {
		// TODO Auto-generated method stub
		return custRepo.viewOrders(orderId);
	}

	


}
