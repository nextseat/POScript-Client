package com.appvoyage.poscript.api.model;

public class OrderItem {

	private String id;
	private String name;
	private int count;
	private long price;
	
	public OrderItem(String id, String name, long price) {
		this.id = id;
		this.name = name;
		this.count = 1;
		this.price = price;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
}
