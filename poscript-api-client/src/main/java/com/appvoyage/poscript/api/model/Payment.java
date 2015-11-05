package com.appvoyage.poscript.api.model;

import java.util.Map;

public class Payment {
	private PaymentType paymentType;
	private Map<String, Object> paymentAttributes;

	public Payment(PaymentType paymentType, Map<String, Object> paymentAttributes) {
		this.paymentType = paymentType;
		this.paymentAttributes = paymentAttributes;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public Map<String, Object> getPaymentAttributes() {
		return paymentAttributes;
	}
}
