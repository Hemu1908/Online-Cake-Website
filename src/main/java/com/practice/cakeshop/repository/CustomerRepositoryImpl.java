package com.practice.cakeshop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.practice.cakeshop.dto.CategoryDto;
import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.dto.ProductDto;
import com.practice.cakeshop.entity.Category;
import com.practice.cakeshop.entity.Customer;
import com.practice.cakeshop.entity.Product;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Customer register(Customer customer) {
		// TODO Handle Exception, add unique constraint to email id
		return em.merge(customer);
	}


	@Override
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
	
	public boolean isProductPresent(String name) {
		return (Long)
				em.createQuery("select count(p.name) from Product p where p.name= :name")
				.setParameter("name", name)
				.getSingleResult() == 1 ? true : false;

	}


	@Override
	public Product addProduct(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setUnitPrice(productDto.getUnitPrice());
		product.setImage(productDto.getImage());
		product.setCategory(findCategoryByName(productDto.getCategory().getName()));
		return em.merge(product);
	}


	@Override
	public boolean isCategoryPresent(String name) {
		return (Long)
				em.createQuery("select count(c.name) from Category c where c.name= :name")
				.setParameter("name", name)
				.getSingleResult() == 1 ? true : false;
	}


	@Override
	public Category addCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		return em.merge(category);
	}


	@Override
	public Category findCategoryByName(String name) {
		return (Category) em.createQuery("select c from Category c where c.name= :name")
				.setParameter("name", name)
				.getSingleResult();
	}
}

