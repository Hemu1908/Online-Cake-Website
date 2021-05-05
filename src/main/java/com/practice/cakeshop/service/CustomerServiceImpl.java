package com.practice.cakeshop.service;

import java.util.List;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.practice.cakeshop.dto.CategoryDto;
import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.dto.ProductDto;
import com.practice.cakeshop.entity.Category;
import com.practice.cakeshop.entity.Customer;
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

//	@Override
//	public void addImage(int productId, Multipart imgName) {
//		Product product = custRepo.findProductByProductId(productId);
//		product.setImage((MultipartFile) imgName);
//		
//	}
	


}
