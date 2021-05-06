package com.practice.cakeshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.cakeshop.dto.CategoryDto;
import com.practice.cakeshop.dto.LoginDto;
import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.dto.ProductDto;
import com.practice.cakeshop.entity.Category;
import com.practice.cakeshop.entity.Customer;
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
	public Product addProduct(@ModelAttribute ProductDto product) {
		try {
			return custService.addProduct(product);
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
	
	@GetMapping(value = "/fetchCategory")
	public List<String> fetchCategoryNames() {
		return custService.fetchCategoryNames();
	}
	
	@GetMapping(value = "/fetchProducts")
	public List<Category> fetchProductsByCategoryName(@RequestParam String categoryName) {
		return custService.fetchProductsByCategoryName(categoryName);
	}

}


