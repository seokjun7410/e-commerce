package com.practice.ecommerce.user.domain.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("LoginId VO 테스트")
class LoginIdTest {

	@Test
	void create() {
		String loginIdString = "loginId";
		LoginId loginId = LoginId.create(loginIdString);

		assertEquals(loginId.getUserId(),loginIdString);
	}
}