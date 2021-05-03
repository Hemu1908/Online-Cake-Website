package com.practice.cakeshop.repository;

import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.entity.Customer;

public interface CustomerRepository {
	
	public Customer register(Customer customer);
	public LoginStatus login(String email, String password);
}
