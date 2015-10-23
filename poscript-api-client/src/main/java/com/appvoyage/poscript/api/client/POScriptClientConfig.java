package com.appvoyage.poscript.api.client;

import com.appvoyage.poscript.api.client.POScriptClient.Version;
import com.appvoyage.poscript.api.model.POSVendor;

public class POScriptClientConfig {

	private POScriptClient.Version version;
	private String endPoint;
	
	private POSVendor posVendor;
	private String merchantId = "XXXXXXXX";
	private String secretKey = "XXXXXXXX";
	
	public POScriptClientConfig(Version version, String endPoint,
			POSVendor posVendor, String merchantId, String secretKey) {
		super();
		this.version = version;
		this.endPoint = endPoint;
		this.posVendor = posVendor;
		this.merchantId = merchantId;
		this.secretKey = secretKey;
	}

	public POScriptClient.Version getVersion() {
		return version;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public POSVendor getPosVendor() {
		return posVendor;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public static POScriptClientConfigBuilder builder() {
		return new POScriptClientConfigBuilder();
	}
	
	public static class POScriptClientConfigBuilder {
		POScriptClient.Version version = POScriptClient.Version.V1;
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
			return new POScriptClientConfig(version, endPoint, posVendor, merchantId, secretKey);
		}
	}
}
