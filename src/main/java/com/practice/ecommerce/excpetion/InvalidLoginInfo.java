package com.practice.ecommerce.excpetion;

public class InvalidLoginInfo extends Exception {

	public InvalidLoginInfo(String messageIfThrow) {
		super(messageIfThrow);
	}
}
