package com.spark.vending.coin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CoinCount {
	
	@Id
	@GeneratedValue
	private long id;
	private String coinType;
	private int count;
	
	public CoinCount() {
		
	}

	public CoinCount(String coinType, int count) {
		super();
		this.coinType = coinType;
		this.count = count;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCoinType() {
		return coinType;
	}

	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	
}
