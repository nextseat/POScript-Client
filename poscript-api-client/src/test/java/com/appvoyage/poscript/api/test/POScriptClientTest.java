package com.appvoyage.poscript.api.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appvoyage.poscript.api.client.POScriptClient;
import com.appvoyage.poscript.api.client.POScriptClientConfig;
import com.appvoyage.poscript.api.client.SimplePOScriptClient;
import com.appvoyage.poscript.api.model.Customer;
import com.appvoyage.poscript.api.model.CustomerSearchCriteria;
import com.appvoyage.poscript.api.model.Merchant;
import com.appvoyage.poscript.api.model.Order;
import com.appvoyage.poscript.api.model.OrderSearchCriteria;
import com.appvoyage.poscript.api.model.POSVendor;
import com.appvoyage.poscript.api.model.Payment;
import com.appvoyage.poscript.api.model.PaymentStatus;
import com.appvoyage.poscript.api.model.PaymentType;

public class POScriptClientTest {

	public static void main(String[] args) {
		String endPoint = "http://www.appvoyage.com/poscript";
		String merchantId = "XXXXXXXX";
		String secretKey = "XXXXXXXX";

		//Build POS client configuration
		POScriptClientConfig clientConfig = POScriptClientConfig.builder()
											.setVersion(POScriptClient.Version.V1)
											.setEndPoint(endPoint)
											.setPosVendor(POSVendor.CLOVER) //setPosVendor(POSVendor.SQUARE) 
											.setMerchantId(merchantId)
											.setSecretKey(secretKey)
											.build();

		//Prepare POS Client
		POScriptClient client = new SimplePOScriptClient(clientConfig);
		
		
		//Get merchant information
		Merchant merchant = client.getMerchant();
		System.out.println("Merchant details - " + merchant);

		
		//Create Customer
		String mobile = "+1987654321";
		Customer newCustomer = client.createCustomer(new Customer("Sagar", "Golla", mobile));
		System.out.println("Newly created customer - " + newCustomer.getCustomerId() + " > " + newCustomer.getCustomerName());
		
		
		//Get all Customers
		List<Customer> allCustomers = client.getAllCustomers();
		for (Customer customer : allCustomers) {
			System.out.println(customer.getCustomerId() + " > " + customer.getCustomerName());
		}


		//Search customers by phone number
		Customer foundCustomer = client.searchCustomers(new CustomerSearchCriteria(mobile));
		System.out.println("Found customer - " + foundCustomer.getCustomerId() + " > " + foundCustomer.getCustomerName());
		
		
		//Find latest orders
		OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria(foundCustomer.getCustomerId());
		List<Order> orders = client.getCustomerOrders(orderSearchCriteria);
		Order latestOrder = orders.get(0);
		

		//Make payment
		if (!latestOrder.getOrderStatus().isPayed()) {
			Map<String, Object> paymentAttributes = new HashMap<String, Object>(); //Additional payment attributes
			paymentAttributes.put("total", latestOrder.getTotal());
			
			Payment payment = new Payment(PaymentType.CREDIT_CARD, paymentAttributes); //Credit card payment
			PaymentStatus status = client.makePayment(latestOrder, payment);
			System.out.println("Payment status - " + status);	
		}
		
	}

}
