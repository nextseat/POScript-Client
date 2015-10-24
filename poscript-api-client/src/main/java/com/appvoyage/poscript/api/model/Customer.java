package com.appvoyage.poscript.api.model;

public class Customer {

	private String customerId;
	private String firstName;
	private String lastName;
	private String mobile;

	public Customer() {}
	public Customer(String firstName, String lastName, String mobile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
	}

	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCustomerName() {
		return firstName + " " + lastName;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String toString() {
		return "[id=" + customerId + ", " + firstName + " "  + lastName + "]";
	}
}
