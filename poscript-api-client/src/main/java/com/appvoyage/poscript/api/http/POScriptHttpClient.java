package com.appvoyage.poscript.api.http;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.appvoyage.poscript.api.client.POScriptClientConfig;
import com.appvoyage.poscript.api.model.Customer;
import com.appvoyage.poscript.api.model.Merchant;
import com.appvoyage.poscript.api.model.Order;
import com.appvoyage.poscript.api.model.OrderItem;
import com.appvoyage.poscript.api.model.POSVendorConfig;

@SuppressWarnings("unchecked")
public class POScriptHttpClient {

	private static final String POSCRIPT_BASE_URL = "http://localhost:8080/AppPod/poscript/client?pos_action=";
	protected static HttpClient client;
	static {
		client = HttpClientBuilder.create().setUserAgent("POScript Client/Next Set 2.0").build();
	}
	
	private POScriptClientConfig clientConfig;
	
	public POScriptHttpClient(POScriptClientConfig clientConfig) {
		this.clientConfig = clientConfig;
	}
	
	protected String getBaseUrl() {
		return (clientConfig.getEndPoint() != null ? clientConfig.getEndPoint() : POSCRIPT_BASE_URL) + "?pos_action=";
	}
	
	protected void setCommonHeaders(HttpRequestBase request) {
		request.setHeader("Accept", "application/json");
		request.setHeader("merchantId", clientConfig.getMerchantId());
		request.setHeader("secretKey", clientConfig.getSecretKey());
		request.setHeader("posVendor", clientConfig.getPosVendor().name());
		request.setHeader("version", clientConfig.getVersion().name());
		request.setHeader("Authorization", "Bearer " + clientConfig.getSecretKey());
	}
	
	public Response get(String url) {
		int statusCode = 500;
		String responseStr = null;
		try {
			HttpGet request = new HttpGet(url);
			setCommonHeaders(request);

			HttpResponse response = client.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
			responseStr = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(statusCode, responseStr);
	}
	
	public static Response getNoHeaders(String url) {
		int statusCode = 500;
		String responseStr = null;
		try {
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
			responseStr = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(statusCode, responseStr);
	}

	public Response post(String url, String payload) {
		int statusCode = 500;
		String responseStr = null;
		try {
			HttpPost request = new HttpPost(url);
			setCommonHeaders(request);
			request.setEntity(new StringEntity(payload));

			HttpResponse response = client.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
			responseStr = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(statusCode, responseStr);
	}
	
	public List<POSVendorConfig> getSupportedPOSVendors() {
		List<POSVendorConfig> vendors = new ArrayList<POSVendorConfig>();
		return vendors;
	}
	
	public Merchant getMerchantInfo() {
		Merchant merchant = new Merchant();
		String url = getBaseUrl() + "merchant_info";
		Response response = get(url);
		JSONObject responseJSON = (JSONObject) JSONValue.parse(response.response);
		merchant.setMerchantId(responseJSON.get("id") == null ? null : responseJSON.get("id").toString());
		merchant.setMerchantName(responseJSON.get("name") == null ? null : responseJSON.get("name").toString());
		merchant.setMobile(responseJSON.get("phoneNumber") == null ? null : responseJSON.get("phoneNumber").toString());
		return merchant;
	}
	
	public Customer searchCustomer(String phoneNumber) {
		String url = getBaseUrl() + "search_customer";
		Response response = get(url);
		JSONObject responseJSON = (JSONObject) JSONValue.parse(response.response);
		JSONArray customersJSONArray = (JSONArray) responseJSON.get("elements");
		JSONObject customerJSON = null;
		JSONObject phoneNumbersJSON = null;
		JSONArray phonesJSONArray = null;
		
		String phone = null;
		Customer customer = null;
		if (customersJSONArray != null) {
			for (int i = 0 ; i < customersJSONArray.size() ; i ++) {
				customerJSON = (JSONObject) customersJSONArray.get(i);
				phoneNumbersJSON = (JSONObject) customerJSON.get("phoneNumbers");
				phonesJSONArray = (JSONArray) phoneNumbersJSON.get("elements");
				
				if (phonesJSONArray == null) {
					continue;
				}
				
				for (int j = 0 ; j < phonesJSONArray.size() ; j ++) {
					phone = String.valueOf(((JSONObject) phonesJSONArray.get(j)).get("phoneNumber"));
					if (phone != null && phone.equals(phoneNumber)) {
						customer = new Customer();
						customer.setCustomerId(String.valueOf(customerJSON.get("id")));
						customer.setFirstName(String.valueOf(customerJSON.get("firstName")));
						customer.setLastName(String.valueOf(customerJSON.get("lastName")));
						customer.setMobile(phoneNumber);
						break;
					}
				}
				
				if (customer != null) {
					break;
				}
			}
		}
		//"marketingAllowed":false,"firstName":"Venkat1","href":"https:\/\/www.clover.com\/v3\/merchants\/HWAGW2SWS9E7G\/customers\/RA66D9R6X0ZTP"}
		return customer;
	}
	
	public Response createCustomer(String lastName, String firstName, String phone, String emailAddress) {
		JSONObject requestPayload = new JSONObject();
		requestPayload.put("lastName", lastName != null ? lastName : "");
		requestPayload.put("firstName", firstName);
		
		JSONArray phoneNumbers = new JSONArray();
		JSONObject phoneNumber = new JSONObject();
		phoneNumber.put("phoneNumber", phone);
		phoneNumbers.add(phoneNumber);
		requestPayload.put("phoneNumbers", phoneNumbers);
		
		JSONObject email = new JSONObject();
		email.put("emailAddress", emailAddress != null ? emailAddress : "");
		JSONArray emailAddresses = new JSONArray();
		emailAddresses.add(email);
		requestPayload.put("emailAddresses", emailAddresses);
		
		String payload = requestPayload.toJSONString();
		String url = getBaseUrl() + "create_customer";
		return post(url, payload);
	}
	
	
	public List<Order> getCustomerOrders(String customerId) {
		List<Order> orders = new ArrayList<Order>();
		String url = getBaseUrl() + "customer_orders&customerId=" + customerId;
		Response response = get(url);
		
		JSONObject responseJSON = (JSONObject) JSONValue.parse(response.response);
		JSONArray ordersJSONArray = (JSONArray) responseJSON.get("elements");
		JSONObject orderJSON = null;
		
		Order order = null;
		String state = null;
		for (int i = 0 ; i < ordersJSONArray.size() ; i ++) {
			orderJSON = (JSONObject) ordersJSONArray.get(i);
			state = String.valueOf(orderJSON.get("state"));
			
			if (state != null && state.equals("open")) {
				order = new Order();
				order.setId(String.valueOf(orderJSON.get("id")));
				order.setCurrency(String.valueOf(orderJSON.get("currency")));
				order.setTotal(Long.parseLong(String.valueOf(orderJSON.get("total"))));
				order.setCreatedTime(Long.parseLong(String.valueOf(orderJSON.get("createdTime"))));
				break;
			}
		}
		
		//statusCode=200, {"elements": [ {"href": "https://www.clover.com/v3/merchants/HWAGW2SWS9E7G/orders/DQS1RXRPQACT2", "id": "DQS1RXRPQACT2", 
		//"currency": "USD", "employee": {"id": "1EY8CKH808PXG"}, "total": 4, "taxRemoved": false, "isVat": false, "state": "open", 
		//"manualTransaction": false, "groupLineItems": true, "testMode": false, "createdTime": 1440525579000, "clientCreatedTime": 1440525578000, "modifiedTime": 1440525587000, "device": {"id": "3aeb21b1-e1e7-483a-80a0-858824a038d6"}}], "href": "https://api.clover.com:443/v3/merchants/HWAGW2SWS9E7G/orders?filter=customer.id%3DRA66D9R6X0ZTP&filter=stateIS%20NOT%20NULL&limit=100"}
		
		if (order != null) {
			url = getBaseUrl() + "order_line_items&orderId=" + order.getId();
			response = get(url);
			
			responseJSON = (JSONObject) JSONValue.parse(response.response);
			JSONObject lineItemsJSON = (JSONObject) responseJSON.get("lineItems");
			JSONArray lineItemsJSONArray = (JSONArray) lineItemsJSON.get("elements");
			
			JSONObject lineItemJSON = null;
			Map<Object, OrderItem> itemGroupMap = new LinkedHashMap<Object, OrderItem>();
			OrderItem item = null;
			for (int i = 0 ; i < lineItemsJSONArray.size() ; i ++) {
				lineItemJSON = (JSONObject) lineItemsJSONArray.get(i);
				
				if (itemGroupMap.containsKey(lineItemJSON.get("name"))) {
					item = itemGroupMap.get(lineItemJSON.get("name"));
					item.setCount(item.getCount() + 1);
					item.setPrice(item.getPrice() + Long.parseLong(String.valueOf(lineItemJSON.get("price"))));
					
				} else {
					item = new OrderItem(
							String.valueOf(lineItemJSON.get("id")), 
							String.valueOf(lineItemJSON.get("name")), 
							Long.parseLong(String.valueOf(lineItemJSON.get("price"))));
					itemGroupMap.put(lineItemJSON.get("name"), item);
				}
				
			}
			
			order.getItems().addAll(itemGroupMap.values());
			orders.add(order);
		}
		
		return orders;
	}
}
