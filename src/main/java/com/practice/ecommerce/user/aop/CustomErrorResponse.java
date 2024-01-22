package com.practice.ecommerce.user.aop;

public class CustomErrorResponse {
	private String message;

	public CustomErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
