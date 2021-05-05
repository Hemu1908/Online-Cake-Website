package com.practice.cakeshop.dto;

import javax.mail.Multipart;

import com.practice.cakeshop.entity.Category;

public class ProductDto {
	
	int productId;
	String name;
	String description;
	double unitPrice;
	Multipart image;
	Category category;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Multipart getImage() {
		return image;
	}
	public void setImage(Multipart image) {
		this.image = image;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
