package com.practice.cakeshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.entity.Customer;
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

}
