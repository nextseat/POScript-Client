package com.appvoyage.poscript.api.client;

import java.util.List;

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

	public SimplePOScriptClient(POScriptClientConfig clientConfig) {
		this.clientConfig = clientConfig;
	}

	@Override
	public List<POSVendorConfig> getSupportedPOSVendors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public POSVendorConfig getPOSVendor(POSVendor posVendor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Merchant getMerchant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer searchCustomers(
			CustomerSearchCriteria customerSearchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAllOrders(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getCustomerOrders(OrderSearchCriteria orderSearchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentStatus makePayment(Order order, Payment payment) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
