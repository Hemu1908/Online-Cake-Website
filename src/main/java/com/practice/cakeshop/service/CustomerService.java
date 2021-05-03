package com.practice.cakeshop.service;

import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.entity.Customer;

public interface CustomerService {
	
	public Customer register(Customer customer);
	public LoginStatus login(String emailId, String password);

}
