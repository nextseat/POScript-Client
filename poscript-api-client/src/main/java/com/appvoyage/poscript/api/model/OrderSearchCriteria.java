package com.appvoyage.poscript.api.model;

public class OrderSearchCriteria {

	private String customerId;
	private String orderId;

	public OrderSearchCriteria(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
