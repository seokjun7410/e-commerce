package com.practice.ecommerce.user.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Embeddable
public class LoginId {

	private String userId;

	public static LoginId create(String loginId) {
		return new LoginId(loginId);
	}
}
