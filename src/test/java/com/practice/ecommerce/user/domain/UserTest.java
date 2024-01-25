package com.practice.ecommerce.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.practice.ecommerce.user.domain.vo.Address;
import com.practice.ecommerce.user.domain.vo.LoginId;
import com.practice.ecommerce.user.domain.vo.Password;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User 도메인 테스트")
class UserTest {

	@Test
	void createStoreOwner() {
		LoginId loginId = LoginId.create("loginId");
		Password password = Password.create("password");
		String doro = "서울특별시 강남구 강남대로 12길 34";
		String jibun = "서울특별시 강남구 역삼동 12-3";
		Address address = Address.create(doro, jibun);
		String name = "name";
		User storeOwner = User.createStoreOwner(loginId, password, address, name);

		assertThat(storeOwner.getLoginId()).usingRecursiveComparison().isEqualTo(loginId);
		assertThat(storeOwner.getPassword()).usingRecursiveComparison().isEqualTo(password);
		assertThat(storeOwner.getPastPassword()).usingRecursiveComparison().isEqualTo(password);
		assertThat(storeOwner.getName()).isEqualTo(name);
		assertThat(storeOwner.getAddress()).usingRecursiveComparison().isEqualTo(address);
		assertThat(storeOwner.isWithDraw()).isFalse();

	}

	@Test
	void updatePassword() {
		LoginId loginId = LoginId.create("loginId");
		Password password = Password.create("password");
		String doro = "서울특별시 강남구 강남대로 12길 34";
		String jibun = "서울특별시 강남구 역삼동 12-3";
		Address address = Address.create(doro, jibun);
		String name = "name";
		User storeOwner = User.createStoreOwner(loginId, password, address, name);
		String newPassword = "newPassword";

		Password encryptPassword = Password.create(newPassword);
		storeOwner.updatePassword(encryptPassword);

		assertThat(storeOwner.getPassword()).usingRecursiveComparison().isEqualTo(encryptPassword);
		assertThat(storeOwner.getPastPassword()).usingRecursiveComparison().isEqualTo(password);
	}
}