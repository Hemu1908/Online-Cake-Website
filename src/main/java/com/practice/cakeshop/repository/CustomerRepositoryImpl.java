package com.practice.cakeshop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

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

}
