package com.appvoyage.poscript.api.http;

public class Response {
	int statusCode;
	String response;

	public Response(int statusCode, String response) {
		this.statusCode = statusCode;
		this.response = response;
	}

	public String toString() {
		return "statusCode=" + statusCode + ", " + response;
	}
}
