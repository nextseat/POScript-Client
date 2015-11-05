package com.appvoyage.poscript.api.model;

public class PaymentStatus {
	
	private String status;
	private String statusMessage;

	public PaymentStatus(String status, String statusMessage) {
		this.status = status;
		this.statusMessage = statusMessage;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}
	
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public String toString() {
		return "PaymentStatus[status=" + status + ", statusMessage=" + statusMessage + "]";
	}
}
