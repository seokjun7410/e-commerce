package com.practice.ecommerce.user.infra.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.practice.ecommerce.ApiTest;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.user.infra.adapter.UserRepository;
import com.practice.ecommerce.user.infra.web.dto.UserDto;
import com.practice.ecommerce.user.step.StoreOwnerStep;
import io.restassured.filter.session.SessionFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("스토어 오너 - API 테스트")
class StoreOwnerApiTest extends ApiTest {

	@Autowired
	private UserRepository userRepository;

	@AfterEach
	void tearDown() {
		userRepository.deleteAll();
		VirtualStoreOwner.clear();
	}

	@Test
	void StoreOwner_회원가입_API() {
		StoreOwnerStep.signUp_API(spec);
	}

	@Test
	void StoreOwner_로그인_API() {
		SessionFilter sessionFilter = new SessionFilter();
		StoreOwnerStep.signUp_API(spec);
		StoreOwnerStep.login_API(spec, sessionFilter);
	}

	@Test
	void StoreOwner_계정정보_조회_API() {
		SessionFilter sessionFilter = new SessionFilter();
		StoreOwnerStep.signUp_API(spec);
		StoreOwnerStep.login_API(spec, sessionFilter);

		UserDto userDto = StoreOwnerStep.MyInfo_API(spec, sessionFilter);

		assertThat(userDto.id()).isNotNull();
		assertThat(userDto.userId()).isEqualTo(VirtualStoreOwner.getLoginId());
		assertThat(userDto.nickName()).isEqualTo(VirtualStoreOwner.getNickName());
		assertThat(userDto.address().getDoro()).isEqualTo(VirtualStoreOwner.getAddress());
		assertThat(userDto.createdTime()).isNotNull();
		assertThat(userDto.updatedTime()).isNotNull();
		assertThat(userDto.isWithDraw()).isFalse();
	}

	@Test
	void StoreOwner_로그아웃_API() {
		SessionFilter sessionFilter = new SessionFilter();
		StoreOwnerStep.signUp_API(spec);
		StoreOwnerStep.login_API(spec, sessionFilter);
		StoreOwnerStep.logout_API(spec, sessionFilter);
	}

	@Test
	void StoreOwner_패스워드_변경_API() {
		SessionFilter sessionFilter = new SessionFilter();

		StoreOwnerStep.signUp_API(spec);
		StoreOwnerStep.login_API(spec, sessionFilter);

		StoreOwnerStep.updatePassword_API(spec, sessionFilter,"newPassword");

		StoreOwnerStep.logout_API(spec, sessionFilter);
		VirtualStoreOwner.setPassword("newPassword");
		StoreOwnerStep.login_API(spec, sessionFilter);
	}


	@Test
	void StoreOwner_삭제_API() {
		SessionFilter sessionFilter = new SessionFilter();
		StoreOwnerStep.signUp_API(spec);
		StoreOwnerStep.login_API(spec, sessionFilter);

		StoreOwnerStep.delete_API(spec, sessionFilter);
	}

}