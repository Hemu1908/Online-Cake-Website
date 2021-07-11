package com.practice.cakeshop.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.practice.cakeshop.dto.CartItemDto;
import com.practice.cakeshop.dto.CategoryDto;
import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.dto.OrderDto;
import com.practice.cakeshop.dto.ProductDto;
import com.practice.cakeshop.entity.Cart;
import com.practice.cakeshop.entity.CartItem;
import com.practice.cakeshop.entity.Category;
import com.practice.cakeshop.entity.Customer;
import com.practice.cakeshop.entity.Expense;
import com.practice.cakeshop.entity.Order;
import com.practice.cakeshop.entity.OrderItem;
import com.practice.cakeshop.entity.OrderStatus;
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
	public Product addProduct(@ModelAttribute ProductDto productDto,HttpServletRequest request) {
		Product product = new Product();
//		String imageUploadLocation = "D:/ProjectGladiator/GitRepo/InsuranceAngular/src/assets/uploads/";
		String projectPath = request.getServletContext().getRealPath("/");
		System.out.println(projectPath);
		
        String imageUploadLocation = projectPath+"/uploads/";
        File f = new File(imageUploadLocation);
        
        if(!f.exists())
        	f.mkdir();
        
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
		product.setImage(targetFile);
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
	@Transactional
	public CartItem addToCart(CartItemDto cartDto) {
		
		//If product is already in the cart for a particular customer
		String jpql = "select c from CartItem c where c.product.productId=:pId and c.cart.customer.customerId=:cId";
		Query query = em.createQuery(jpql);
		query.setParameter("pId", cartDto.getProductId());
		query.setParameter("cId", cartDto.getCustomerId());
		try {
			CartItem cartItem = (CartItem)query.getSingleResult();
			System.out.println(cartItem.getQuantity());
			cartItem.setQuantity(cartItem.getQuantity()+1);
			return em.merge(cartItem);
		} catch(NoResultException nre) {
			
			//If a cart is available in cart table for a customer
			String jpql1 = "select c from Cart c where c.customer.customerId=:cId";
			Query query1 = em.createQuery(jpql1);
			query1.setParameter("cId", cartDto.getCustomerId());
			
			try {
				CartItem cartItem = new CartItem();
				Cart cart = (Cart)query1.getSingleResult();
				System.out.println(cart.getCartId());
				cartItem.setCart(cart);
				cartItem.setProduct(findProductByProductId(cartDto.getProductId()));
				cartItem.setQuantity(cartDto.getQuantity());
				return em.merge(cartItem);
				
			} catch(NoResultException e) {
				//Adding new product for new customer
				try {
					System.out.println("In first exception");
					Cart cart = new Cart();
					cart.setCustomer(findCustomerById(cartDto.getCustomerId()));
					cart = em.merge(cart);
					CartItem cartItem = new CartItem();
					cartItem.setCart(cart);
					cartItem.setProduct(findProductByProductId(cartDto.getProductId()));
					cartItem.setQuantity(cartDto.getQuantity());
					return em.merge(cartItem);
				} catch(NoResultException ne) {
					return null;
				}
			}
		}
	}


	@Override
	public Customer findCustomerById(int customerId) {
		return em.find(Customer.class, customerId);
	}


	@Override
	public Cart displayAllItemsOfCart(int customerId) {
		String jpql = "select c from Cart c where c.customer.customerId = :custId";
		Query q = em.createQuery(jpql);
		q.setParameter("custId", customerId);
		try {
			return (Cart) q.getSingleResult();
		} catch(NoResultException nre) {
			return null;
		}
		
	}


	@Override
	@Transactional
	public Order placeOrder(OrderDto orderDto) {
		// TODO Auto-generated method stub
		Order order = new Order();
		order.setCustomer(findCustomerById(orderDto.getCustomerId()));
		order.setAmount(orderDto.getAmount());
		order.setOrderedDateTime(LocalDate.now());
		order.setShippingDate(orderDto.getShippingDateTime());
		order.setShippingAddress(orderDto.getShippingAddress());
		order.setTimeslot(orderDto.getDeliveryTime());
		order.setOrderStatus(OrderStatus.PENDING);
		
		order = em.merge(order);
		
//		System.out.println(findCartById(orderDto.getCartId()).getCartItems());
		List<CartItem> cartItem = findCartById(orderDto.getCartId()).getCartItems();
		for (CartItem element : cartItem) {
			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setProduct(element.getProduct());
			item.setPrice(element.getProduct().getUnitPrice());
			item.setQuantity(element.getQuantity());
			item = em.merge(item);
			
		}
		int rec = deleteCartItem(orderDto.getCartId());
		System.out.println(rec);
		rec = deleteCart(orderDto.getCartId());
		System.out.println(rec);
		return order;
	}


	@Override
	public CartItem findCartItemById(int cartItemId) {
		// TODO Auto-generated method stub
		return em.find(CartItem.class, cartItemId);
	}


	@Override
	public List<Order> viewOrders(int customerId) {
		String jpql = "select o from Order o where o.customer.customerId = :customerId order by o.orderId desc";
		Query q = em.createQuery(jpql);
		q.setParameter("customerId", customerId);
		return q.getResultList();
	}


	@Override
	public Cart findCartById(int cartId) {
		return em.find(Cart.class, cartId);
	}


	@Override
	public int deleteCart(int cartId) {
		// TODO Auto-generated method stub
		String jpql = "delete from Cart c where c.cartId=:cId";
		Query query = em.createQuery(jpql);
		query.setParameter("cId", cartId);
		int rec = query.executeUpdate();
		
//		em.detach(findCartById(cartId));
		return rec;
		
	}


	@Override
	public int deleteCartItem(int cartId) {
		String jpql = "delete from CartItem c where c.cart.cartId=:cId";
		Query query = em.createQuery(jpql);
		query.setParameter("cId", cartId);
		int rec = query.executeUpdate();
		return rec;
	}


	@Override
	public Order findOrdersByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return em.find(Order.class, customerId);
	}


	@Override
	public List<Order> viewOrdersByStatus(OrderStatus status) {
		String jpql = "select o from Order o where o.orderStatus=:oStatus";
		Query query = em.createQuery(jpql);
		query.setParameter("oStatus", status);
		return query.getResultList();
	}


	@Override
	public Order changeOrderStatus(int orderId, OrderStatus status) {
		// TODO Auto-generated method stub
		Order order = findOrderById(orderId);
		order.setOrderStatus(status);
		return em.merge(order);
	}


	@Override
	public Order findOrderById(int orderId) {
		// TODO Auto-generated method stub
		return em.find(Order.class, orderId);
	}


	@Override
	@Transactional
	public Expense addExpense(Expense expense) {
		// TODO Auto-generated method stub
		return em.merge(expense);
	}


	@Override
	public List<Expense> viewAllExpense() {
		// TODO Auto-generated method stub
		String jpql = "select e from Expense e order by e.orderDate desc";
		Query query = em.createQuery(jpql);
		
		return query.getResultList();
	}
	
}

