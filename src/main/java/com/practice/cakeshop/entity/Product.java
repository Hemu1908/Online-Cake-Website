package com.practice.cakeshop.entity;

import javax.mail.Multipart;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "shop_product")
public class Product {
	
	@Id
	@SequenceGenerator(name="customer_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="product_seq")
	@Column(name = "product_id")
	int productId;
	String name;
	String description;
	double unitPrice;
	Multipart image;
	
	@ManyToOne 
	@JoinColumn(name = "category_id")
	private Category category;
	
	

}
