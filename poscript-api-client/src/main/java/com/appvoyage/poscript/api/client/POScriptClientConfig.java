package com.appvoyage.poscript.api.client;

import com.appvoyage.poscript.api.model.POSVendor;

public class POScriptClientConfig {

	public static POScriptClientConfigBuilder builder() {
		return new POScriptClientConfigBuilder();
	}
	
	public static class POScriptClientConfigBuilder {
		POScriptClient.Version version;
		String endPoint;
		
		POSVendor posVendor;
		String merchantId = "XXXXXXXX";
		String secretKey = "XXXXXXXX";
		
		public POScriptClientConfigBuilder setEndPoint(String endPoint) {
			this.endPoint = endPoint;
			return this;
		}

		public POScriptClientConfigBuilder setVersion(POScriptClient.Version version) {
			this.version = version;
			return this;
		}

		public POScriptClientConfigBuilder setPosVendor(POSVendor posVendor) {
			this.posVendor = posVendor;
			return this;
		}

		public POScriptClientConfigBuilder setMerchantId(String merchantId) {
			this.merchantId = merchantId;
			return this;
		}

		public POScriptClientConfigBuilder setSecretKey(String secretKey) {
			this.secretKey = secretKey;
			return this;
		}
		
		public POScriptClientConfig build() {
			return null;
		}
	}
}
