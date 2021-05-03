package com.practice.cakeshop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.entity.Customer;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	
	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public Customer register(Customer customer) {
		// TODO Handle Exception, add unique constraint to email id
		return em.merge(customer);
	}


	@Override
	@Transactional
	public LoginStatus login(String email, String password) {
		String jpql = "select c from Customer c where c.email= :email AND c.password= :pwd";
		Query query = em.createQuery(jpql);
		query.setParameter("email", email);
		query.setParameter("pwd", password);
		LoginStatus lstatus = new LoginStatus();
		try {
			Customer customer = (Customer) query.getSingleResult();
			lstatus.setCustomerId(customer.getCustomerId());
			lstatus.setEmailId(customer.getEmail());
			lstatus.setName(customer.getName());
			lstatus.setStatus(true);
			lstatus.setMessage("Login Successful");
			return lstatus;
		}
		catch(Exception e) {
			lstatus.setStatus(false);
			lstatus.setMessage("Invalid emailId or password");
			return lstatus;
		}
		
	}
}

