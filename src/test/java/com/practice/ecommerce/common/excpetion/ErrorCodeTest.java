package com.practice.ecommerce.common.excpetion;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;


class ErrorCodeTest {

	@Test
	public void appendId() {
		ErrorCode preErrorCode = ErrorCode.STORE_OWNER_IS_NOT_FOUND;
		String preDetail = preErrorCode.getDetail();
		ErrorCode appendedErrorCode = preErrorCode.appendId("1234");

		assertThat(appendedErrorCode.getDetail()).isEqualTo(preDetail+" "+"1234");
	}
}