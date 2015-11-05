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
import com.appvoyage.poscript.api.model.OrderItem;
import com.appvoyage.poscript.api.model.OrderSearchCriteria;
import com.appvoyage.poscript.api.model.POSVendor;
import com.appvoyage.poscript.api.model.Payment;
import com.appvoyage.poscript.api.model.PaymentStatus;
import com.appvoyage.poscript.api.model.PaymentType;

public class POScriptRestClientTest {

	public static void main(String[] args) {
		
		String endPoint = "http://appvoyage.com/poscript";
		String merchantId = "HWAGW2SWS9E7G";
		String secretKey = " d9a63eae-1040-0988-4947-78a45c8c7e6e";
		
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
		
		//Search customer
		String customerMobile = "+91999998888";
		CustomerSearchCriteria customerSearchCriteria = new CustomerSearchCriteria(customerMobile);
		Customer customer = client.searchCustomers(customerSearchCriteria);
		System.out.println("Customer details - " + customer);
		
		if (customer == null) {
			customer = new Customer("Sagar", "POScriptClientTest", customerMobile);
			client.createCustomer(customer);
			System.out.println("Customer details - " + customer);
		}
		
		
		//Customer Orders
		OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria(customer.getCustomerId());
		List<Order> orders = client.getCustomerOrders(orderSearchCriteria);
		Order recentOpenOrder = null;
		
		for (Order order : orders) {
			if (recentOpenOrder == null && order.getOrderStatus().isOpen()) {
				recentOpenOrder = order;
			}
			
			System.out.println("Order - " + order);
			for (OrderItem item : order.getItems()) {
				System.out.println(" - " + item.getName() + "(" + item.getCount() + ") " + item.getPrice());	
			}
		}
		
		//Make payment
		if (recentOpenOrder != null) {
			System.out.println("Making payment for Order - " + recentOpenOrder);
			
			Map<String, Object> paymentAttributes = new HashMap<String, Object>(); //Additional payment attributes
			paymentAttributes.put("pan", "4111111111111111");
			paymentAttributes.put("expMonth", "1");
			paymentAttributes.put("expYear", "2018");
			paymentAttributes.put("cvv", "111");
			
			Payment payment = new Payment(PaymentType.CREDIT_CARD, paymentAttributes); //Credit card payment
			PaymentStatus status = client.makePayment(recentOpenOrder, payment);
			System.out.println("Payment status - " + status);	
		}
	}
}
