package com.practice.cakeshop.repository;

import java.util.List;

import com.practice.cakeshop.dto.CategoryDto;
import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.dto.ProductDto;
import com.practice.cakeshop.entity.Category;
import com.practice.cakeshop.entity.Customer;
import com.practice.cakeshop.entity.Product;

public interface CustomerRepository {
	
	public Customer register(Customer customer);
	public LoginStatus login(String email, String password);
	public boolean isProductPresent(String name);
	public Product addProduct(ProductDto productDto);
	public boolean isCategoryPresent(String name);
	public Category addCategory(CategoryDto categoryDto);
	public Category findCategoryByName(String name);
	public Product findProductByProductId(int productId);
	public List<String> fetchCategoryNames();
	public List<Category> fetchProductsByCategoryName(String categoryName);
}
