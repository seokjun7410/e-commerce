package com.practice.ecommerce.user.docs;

import com.practice.ecommerce.user.infra.web.dto.StoreOwnerRegisterRequest;
import com.practice.ecommerce.user.infra.web.dto.UserLoginRequest;

public class UserLoginDocs {

	public static class Request {

		public static UserLoginRequest create() {
			StoreOwnerRegisterRequest storeOwnerRegisterRequest = StoreOwnerSignUpDocs.Request.create();
			String password = storeOwnerRegisterRequest.password();
			String loginId = storeOwnerRegisterRequest.loginId();
			return new UserLoginRequest(loginId, password);

		}
	}
}
