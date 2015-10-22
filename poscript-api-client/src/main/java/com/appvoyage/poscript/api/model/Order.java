package com.appvoyage.poscript.api.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

	public static enum OrderStatus {
		OPEN,
		PAYED;
		
		public boolean isPayed() {
			return this == PAYED;
		}
	}
	
	private String id;
	private List<OrderItem> items = new ArrayList<OrderItem>();
	private long total;
	private String currency;
	private long createdTime;
	private OrderStatus orderStatus;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
