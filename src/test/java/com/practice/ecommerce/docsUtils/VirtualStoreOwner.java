package com.practice.ecommerce.docsUtils;

public class VirtualStoreOwner {
	private static String loginId = "hoding";
	private static String password = "pa22@rd";
	private static String nickName = "스프링코더";
	private static String address = "서울시 강남구 역삼동 28-3";

	public static void clear(){
		nickName = "스프링코더";
		loginId = "hoding";
		address = "서울시 강남구 역삼동 28-3";
		password = "pa22@rd";
	}
	public static String getLoginId() {
		return loginId;
	}

	public static String getPassword() {
		return password;
	}

	public static String getNickName() {
		return nickName;
	}

	public static String getAddress() {
		return address;
	}


	public static void setLoginId(String loginId) {
		VirtualStoreOwner.loginId = loginId;
	}

	public static void setNickName(String nickName) {
		VirtualStoreOwner.nickName = nickName;
	}

	public static void setAddress(String address) {
		VirtualStoreOwner.address = address;
	}

	public static void setPassword(String password) {
		VirtualStoreOwner.password = password;
	}
}
