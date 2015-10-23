package com.appvoyage.poscript.api.test;

import com.appvoyage.poscript.api.client.POScriptClient;
import com.appvoyage.poscript.api.client.POScriptClientConfig;
import com.appvoyage.poscript.api.client.SimplePOScriptClient;
import com.appvoyage.poscript.api.model.Merchant;
import com.appvoyage.poscript.api.model.POSVendor;

public class POScriptRestClientTest {

	public static void main(String[] args) {
		
		String endPoint = "http://appvoyage.com/poscript/client";
		String merchantId = "Z19R9MTJ745RC";
		String secretKey = "40dec1cd-1f83-88a0-29de-30e7fadd4e8e";
		
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
		
	}
}
