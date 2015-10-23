package com.appvoyage.poscript.api.http;

import java.util.ArrayList;
import java.util.List;

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
import com.appvoyage.poscript.api.model.Merchant;
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
		String url = POSCRIPT_BASE_URL + "merchant_info";
		Response response = get(url);
		JSONObject responseJSON = (JSONObject) JSONValue.parse(response.response);
		merchant.setMerchantId(responseJSON.get("id") == null ? null : responseJSON.get("id").toString());
		merchant.setMerchantName(responseJSON.get("name") == null ? null : responseJSON.get("name").toString());
		merchant.setMobile(responseJSON.get("phoneNumber") == null ? null : responseJSON.get("phoneNumber").toString());
		return merchant;
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
		String url = POSCRIPT_BASE_URL + "create_customer";
		return post(url, payload);
	}
	
}
