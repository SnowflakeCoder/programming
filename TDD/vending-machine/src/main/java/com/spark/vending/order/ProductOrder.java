package com.spark.vending.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductOrder {
	
	@Id
	@GeneratedValue
	private long id;
	
	private long productId;
	private int productCount;
	private int acceptedAmount;
	private String orderStatus;
	private int orderAmount;
	
	/*
	 * default constructor
	 * 
	 */
	
	public ProductOrder() {
	}
	
	public ProductOrder(long productId, int productCount, int acceptedAmount, String orderStatus, int orderAmount) {
		super();
		this.productId = productId;
		this.productCount = productCount;
		this.acceptedAmount = acceptedAmount;
		this.orderStatus = orderStatus;
		this.orderAmount = orderAmount;
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

	public int getAcceptedAmount() {
		return acceptedAmount;
	}

	public void setAcceptedAmount(int acceptedAmount) {
		this.acceptedAmount = acceptedAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	} 
	
	
}
