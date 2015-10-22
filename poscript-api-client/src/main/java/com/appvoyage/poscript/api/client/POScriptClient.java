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

public interface POScriptClient {
	
	public enum Version {
		V1, V2, V3;
	}

    //get /poscript/{version}/pos
	public List<POSVendorConfig> getSupportedPOSVendors();
	
    //get /poscript/{version}/pos/{posId}
	public POSVendorConfig getPOSVendor(POSVendor posVendor);

    //get /poscript/{version}/merchants/{merchantId}
	public Merchant getMerchant();

    //get /poscript/{version}/merchants/{merchantId}/customers
	public List<Customer> getAllCustomers();

    //get /poscript/{version}/merchants/{merchantId}/customers/{customerId}
	public Customer getCustomer(String customerId);
	
    //post /poscript/{version}/merchants/{merchantId}/customers/create
	public Customer createCustomer(Customer customer);

    //get /poscript/{version}/merchants/{merchantId}/customers/search
	public Customer searchCustomers(CustomerSearchCriteria customerSearchCriteria);

    //get /poscript/{version}/merchants/{merchantId}/orders 
	public List<Order> getAllOrders(String customerId);
	
    //get /poscript/{version}/merchants/{merchantId}/customers/{customerId}/orders
	public List<Order> getCustomerOrders(OrderSearchCriteria orderSearchCriteria);

    //post /poscript/{version}/merchants/{merchantId}/orders/{orderId}/payments
	public PaymentStatus makePayment(Order order, Payment payment);
	
}
