package com.practice.cakeshop.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.practice.cakeshop.dto.CartItemDto;
import com.practice.cakeshop.dto.CategoryDto;
import com.practice.cakeshop.dto.LoginStatus;
import com.practice.cakeshop.dto.NotificationRequestDto;
import com.practice.cakeshop.dto.OrderDto;
import com.practice.cakeshop.dto.ProductDto;
import com.practice.cakeshop.entity.Cart;
import com.practice.cakeshop.entity.CartItem;
import com.practice.cakeshop.entity.Category;
import com.practice.cakeshop.entity.Customer;
import com.practice.cakeshop.entity.Expense;
import com.practice.cakeshop.entity.Order;
import com.practice.cakeshop.entity.OrderStatus;
import com.practice.cakeshop.entity.Product;
import com.practice.cakeshop.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Value("${app.firebase-config}")
    private String firebaseConfig;

    private FirebaseApp firebaseApp;

    @PostConstruct
    private void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfig).getInputStream())).build();

            if (FirebaseApp.getApps().isEmpty()) {
                this.firebaseApp = FirebaseApp.initializeApp(options);
            } else {
                this.firebaseApp = FirebaseApp.getInstance();
            }
        } catch (IOException e) {
            System.out.println("Create FirebaseApp Error" + e);
        }
    }
	
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
	public Product addProduct(ProductDto product, HttpServletRequest request) {
		return custRepo.addProduct(product,request);
		
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

	@Override
	public List<Category> fetchProductsByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return custRepo.fetchProductsByCategoryName(categoryName);
	}

	@Override
	public CartItem addToCart(CartItemDto cartDto) {
		return custRepo.addToCart(cartDto);
	}

	@Override
	public Cart displayItemsOfCart(int customerId) {
		// TODO Auto-generated method stub
		return custRepo.displayAllItemsOfCart(customerId);
	}

	@Override
	public Order placeOrder(OrderDto orderDto) {
		// TODO Auto-generated method stub
		return custRepo.placeOrder(orderDto);
	}

	@Override
	public List<Order> viewOrders(int customerId) {
		// TODO Auto-generated method stub
		return custRepo.viewOrders(customerId);
	}

	@Override
	public List<Order> viewOrdersByStatus(OrderStatus status) {
		// TODO Auto-generated method stub
		return custRepo.viewOrdersByStatus(status);
	}

	@Override
	public Order changeOrderStatus(int orderId, OrderStatus status) {
		// TODO Auto-generated method stub
		return custRepo.changeOrderStatus(orderId, status);
	}

	@Override
	public Expense addExpense(Expense expense) {
		// TODO Auto-generated method stub
		return custRepo.addExpense(expense);
	}

	@Override
	public List<Expense> viewAllExpense() {
		// TODO Auto-generated method stub
		return custRepo.viewAllExpense();
	}
	
	@Override
	public String sendPnsToDevice(NotificationRequestDto notificationRequestDto) {
		Notification notification = Notification.builder().setTitle(notificationRequestDto.getTitle()).setBody(notificationRequestDto.getBody()).build();
        Message message = Message.builder()
                .setToken(notificationRequestDto.getTarget())
                .setNotification(notification)
                .putData("content", notificationRequestDto.getTitle())
                .putData("body", notificationRequestDto.getBody())
                .build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            System.out.println("Fail to send firebase notification"+ e);
        }

        return response;
    }


}
