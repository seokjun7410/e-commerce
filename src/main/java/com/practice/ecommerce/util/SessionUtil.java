package com.practice.ecommerce.util;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {
	private static final String LOGIN_STORE_OWNER_ID = "LOGIN_STORE_OWNER_ID";
	private static final String LOGIN_USER_ID = "LOGIN_USER_ID";
	private static final String LOGIN_ADMIN_ID = "LOGIN_ADMIN_ID";
	private SessionUtil() {
	}
	public static String getLoginStoreOwnerId(HttpSession session) {
		return (String) session.getAttribute(LOGIN_STORE_OWNER_ID);
	}
	public static void setLoginStoreOwnerId(HttpSession session, String id) {
		session.setAttribute(LOGIN_STORE_OWNER_ID, id);
	}
	public static String getLoginUserId(HttpSession session) {
		return (String) session.getAttribute(LOGIN_USER_ID);
	}
	public static void setLoginUserId(HttpSession session, String id) {
		session.setAttribute(LOGIN_USER_ID, id);
	}
	public static String getLoginAdminId(HttpSession session) {
		return (String) session.getAttribute(LOGIN_ADMIN_ID);
	}
	public static void setLoginAdminId(HttpSession session, String id) {
		session.setAttribute(LOGIN_ADMIN_ID, id);
	}
	public static void clear(HttpSession session) {
		session.invalidate();
	}
}
