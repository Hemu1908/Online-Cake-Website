package com.practice.cakeshop.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "shop_category")
public class Category {
	
	@Id
	@SequenceGenerator(name="customer_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="category_seq")
	@Column(name = "category_id")
	int categoryId;
	String name;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "category")
	private List<Product> products;
}
