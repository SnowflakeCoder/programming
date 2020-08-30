package com.spark.vending.productInventory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductInventory {
	
	@Id
	@GeneratedValue
	private long id;
	
	private long productId;
	
	private int productCount;
	
	/*
	 * default constructor for repository
	 */
	
	public ProductInventory() {
		
	}

	public ProductInventory(long productId, int productCount) {
		super();
		this.productId = productId;
		this.productCount = productCount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
	
	
}
