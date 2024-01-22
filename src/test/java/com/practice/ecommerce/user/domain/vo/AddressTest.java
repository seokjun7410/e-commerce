package com.practice.ecommerce.user.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("Address VO 테스트")
class AddressTest {

	@Test
	void create() {
		String doro = "서울특별시 강남구 강남대로 12길 34";
		String jibun = "서울특별시 강남구 역삼동 12-3";
		Address address = Address.create(doro, jibun);

		assertThat(address.getDoro()).isEqualTo(doro);
		assertThat(address.getJibun()).isEqualTo(jibun);
		assertThat(address.getSi()).isEqualTo("서울특별시");
		assertThat(address.getGu()).isEqualTo("강남구");
	}
}