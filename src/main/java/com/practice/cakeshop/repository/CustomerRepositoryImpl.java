package com.practice.cakeshop.repository;

import java.io.FileOutputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.practice.cakeshop.dto.CartItemDto;
import com.practice.cakeshop.dto.CategoryDto;
import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.dto.ProductDto;
import com.practice.cakeshop.entity.Cart;
import com.practice.cakeshop.entity.CartItem;
import com.practice.cakeshop.entity.Category;
import com.practice.cakeshop.entity.Customer;
import com.practice.cakeshop.entity.Product;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository{
	
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
	public Product addProduct(@ModelAttribute ProductDto productDto) {
		Product product = new Product();
//		String imageUploadLocation = "D:/ProjectGladiator/GitRepo/InsuranceAngular/src/assets/uploads/";
        String imageUploadLocation = "c:/uploads/";
        String fileName = productDto.getImage().getOriginalFilename();
       String targetFile = imageUploadLocation + fileName;
       try {
           FileCopyUtils.copy(productDto.getImage().getInputStream(), new FileOutputStream(targetFile));
       }
       catch (Exception e) {
           e.printStackTrace();
       }
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setUnitPrice(Double.parseDouble(productDto.getUnitPrice()));
		product.setImage(fileName);
		product.setCategory(findCategoryByName(productDto.getCategoryName()));
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


	@Override
	public Product findProductByProductId(int productId) {
		return em.find(Product.class, productId);
	}


	@Override
	public List<String> fetchCategoryNames() {
		// TODO Auto-generated method stub
		String jpql = "select c.name from Category c";
		return (List<String>)em.createQuery(jpql).getResultList();
	}


	@Override
	public List<Category> fetchProductsByCategoryName(String categoryName) {
		String jpql = "select c.products from Category c where c.name=:cname";
		
		Query query = em.createQuery(jpql);
		query.setParameter("cname", categoryName);
		return query.getResultList();
	}


	@Override
	public CartItem addToCart(CartItemDto cartDto) {
		
		Cart cart = new Cart();
		cart.setCustomer(findCustomerById(cartDto.getCustomerId()));
		cart.setCartId(cartDto.getCartId());
		cart = em.merge(cart);
		CartItem cartItem = new CartItem();
		cartItem.setCartItemId(cartDto.getCartItemId());
		cartItem.setCart(cart);
		cartItem.setProduct(findProductByProductId(cartDto.getProductId()));
		cartItem.setQuantity(cartDto.getQuantity());
//		Cart cart = new Cart();
//		cart.setCartId(cartItem.getCart().getCartId());
//		cart.setCustomer(cartDto.getCustomerId());
		
		return em.merge(cartItem);
	}


	@Override
	public Customer findCustomerById(int customerId) {
		return em.find(Customer.class, customerId);
	}


	
	
	
}

