package com.practice.ecommerce.user.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.practice.ecommerce.util.SHA256Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("패스워드 VO 테스트")
class PasswordTest {

	@Test
	void create() {
		String encrypt = SHA256Util.encryptSHA256("password");
		Password password = Password.create("password");

		assertThat(password.getPassword()).isEqualTo(encrypt);
	}
}