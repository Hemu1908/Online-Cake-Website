package com.practice.cakeshop.service;

import javax.mail.Multipart;

import com.practice.cakeshop.dto.CategoryDto;
import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.dto.ProductDto;
import com.practice.cakeshop.entity.Category;
import com.practice.cakeshop.entity.Customer;
import com.practice.cakeshop.entity.Product;

public interface CustomerService {
	
	public Customer register(Customer customer);
	public LoginStatus login(String emailId, String password);
	public Product addProduct(ProductDto product);
	public Category addCategory(CategoryDto category);
	//public void addImage(int productId, Multipart imgName);

}
