package com.practice.ecommerce.user.domain.vo;

import com.practice.ecommerce.util.SHA256Util;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Password {

	private String password;

	public static Password create(String password) {
		String encryptSHA256 = SHA256Util.encryptSHA256(password);
		return new Password(encryptSHA256);
	}
}
