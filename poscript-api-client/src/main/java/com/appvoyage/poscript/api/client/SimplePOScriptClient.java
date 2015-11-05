package com.appvoyage.poscript.api.client;

import java.util.List;

import com.appvoyage.poscript.api.http.POScriptHttpClient;
import com.appvoyage.poscript.api.model.Customer;
import com.appvoyage.poscript.api.model.CustomerSearchCriteria;
import com.appvoyage.poscript.api.model.Merchant;
import com.appvoyage.poscript.api.model.Order;
import com.appvoyage.poscript.api.model.OrderSearchCriteria;
import com.appvoyage.poscript.api.model.POSVendor;
import com.appvoyage.poscript.api.model.POSVendorConfig;
import com.appvoyage.poscript.api.model.Payment;
import com.appvoyage.poscript.api.model.PaymentStatus;

public class SimplePOScriptClient implements POScriptClient {

	private POScriptClientConfig clientConfig;
	private POScriptHttpClient httpClient;

	public SimplePOScriptClient(POScriptClientConfig clientConfig) {
		this.clientConfig = clientConfig;
		this.httpClient = new POScriptHttpClient(clientConfig);
	}

	public List<POSVendorConfig> getSupportedPOSVendors() {
		return httpClient.getSupportedPOSVendors();
	}

	public POSVendorConfig getPOSVendor(POSVendor posVendor) {
		return null;
	}

	public Merchant getMerchant() {
		return httpClient.getMerchantInfo();
	}

	public List<Customer> getAllCustomers() {
		return httpClient.getAllCustomers();
	}

	public Customer getCustomer(String customerId) {
		return httpClient.getCustomer(customerId);
	}

	public Customer createCustomer(Customer customer) {
		return httpClient.createCustomer(customer);
	}

	public Customer searchCustomers(CustomerSearchCriteria customerSearchCriteria) {
		return httpClient.searchCustomer(customerSearchCriteria.getMobile());
	}

	public List<Order> getAllMerchantOrders() {
		return null;
	}

	public List<Order> getCustomerOrders(OrderSearchCriteria orderSearchCriteria) {
		return httpClient.getCustomerOrders(orderSearchCriteria.getCustomerId());
	}

	public PaymentStatus makePayment(Order order, Payment payment) {
		payment.getPaymentAttributes().put("total", order.getTotal());
		payment.getPaymentAttributes().put("orderId", order.getId());
		return httpClient.makePayment(order, payment);
	}
	
}
