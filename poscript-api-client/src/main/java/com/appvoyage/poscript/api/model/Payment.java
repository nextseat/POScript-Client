package com.appvoyage.poscript.api.model;

import java.util.Map;

public class Payment {
	private long total;

	public Payment(PaymentType paymentType, Map<String, Object> paymentAttributes) {
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
