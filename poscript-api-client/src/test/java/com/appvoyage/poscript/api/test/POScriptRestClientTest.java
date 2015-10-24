package com.appvoyage.poscript.api.test;

import java.util.List;

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

public class POScriptRestClientTest {

	public static void main(String[] args) {
		
		String endPoint = "http://appvoyage.com/poscript/client";
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
		CustomerSearchCriteria customerSearchCriteria = new CustomerSearchCriteria("+919886326550");
		Customer customer = client.searchCustomers(customerSearchCriteria);
		System.out.println("Customer details - " + customer);
		
		
		//Customer Orders
		OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria(customer.getCustomerId());
		List<Order> orders = client.getCustomerOrders(orderSearchCriteria);
		for (Order order : orders) {
			System.out.println("Order - " + order);
			for (OrderItem item : order.getItems()) {
				System.out.println(" - " + item.getName() + "(" + item.getCount() + ") " + item.getPrice());	
			}
		}
		
	}
}
