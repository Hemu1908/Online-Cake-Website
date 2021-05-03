package com.practice.cakeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.cakeshop.entity.Customer;
import com.practice.cakeshop.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService custService;
	
	@PostMapping(value="/register")
	public Customer register(@RequestBody Customer customer ) {
		return custService.register(customer);
	}

}
