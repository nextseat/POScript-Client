package com.appvoyage.poscript.api.model;

public class Customer {

	private String customerId;
	private String customerName;
	private String mobile;

	public Customer(String customerName, String mobile) {
		this.customerName = customerName;
		this.mobile = mobile;
	}

	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
