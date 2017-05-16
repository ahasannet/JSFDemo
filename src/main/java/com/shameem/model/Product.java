package com.shameem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@Column(name="id")
    private int id;
	
	@Column(name="product_name", unique=true, nullable=false)
	private String productName;
	
	@Column(name="price", nullable=false)
	private String price;
	
	@Transient
	private boolean editable;
	
	public Product()
	{
		
	}
	
	public Product (int id, String productName, String price) {
		this.id = id;
		this.productName = productName;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public boolean isEditable() {
		return editable;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}
