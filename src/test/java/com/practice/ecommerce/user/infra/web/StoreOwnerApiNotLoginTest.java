package com.practice.ecommerce.user.infra.web;

import com.practice.ecommerce.ApiTest;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.user.docs.StoreOwnerDeleteDocs;
import com.practice.ecommerce.user.docs.StoreOwnerMyInfoDocs;
import com.practice.ecommerce.user.docs.StoreOwnerUpdatePasswordDocs;
import com.practice.ecommerce.user.infra.adapter.UserRepository;
import com.practice.ecommerce.user.infra.web.dto.UserDeleteRequest;
import com.practice.ecommerce.user.infra.web.dto.UserPasswordUpdateRequest;
import com.practice.ecommerce.user.step.StoreOwnerStep;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("스토어 오너 - 로그인X - API 테스트")
class StoreOwnerApiNotLoginTest extends ApiTest {

	@Autowired
	private UserRepository userRepository;

	@AfterEach
	void tearDown() {
		userRepository.deleteAll();
		VirtualStoreOwner.clear();
	}

	@Test
	void StoreOwner_계정정보_조회_로그인X_UNAUTHORIZED() {
		SessionFilter sessionFilter = new SessionFilter();
		StoreOwnerStep.signUp_API(spec);

		StoreOwnerMyInfoDocs docs = new StoreOwnerMyInfoDocs(null);
		RestAssured.given(spec).log().all()
			.filter(docs.validationFilterInRestAssured(Identifier.STORE_OWNER_MY_INFO_401_UNAUTHORIZED))
			.filter(sessionFilter)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.when()
			.get("/store-owner/my-info")
			.then()
			.statusCode(HttpStatus.UNAUTHORIZED.value())
			.log().all().extract();

	}
	@Test
	void StoreOwner_패스워드_변경_로그인X_UNAUTHORIZED() {
		SessionFilter sessionFilter = new SessionFilter();
		StoreOwnerUpdatePasswordDocs docs = new StoreOwnerUpdatePasswordDocs(
			UserPasswordUpdateRequest.class);

		StoreOwnerStep.signUp_API(spec);

		RestAssured.given(spec).log().all()
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.filter(docs.validationFilterInRestAssured(Identifier.STORE_OWNER_UPDATE_PASSWORD_401_UNAUTHORIZED))
			.filter(sessionFilter)
			.body(StoreOwnerUpdatePasswordDocs.requestCreate("newPassword"))
			.when()

			.patch("/store-owner/password")
			.then()
			.statusCode(HttpStatus.UNAUTHORIZED.value())
			.log().all().extract();
	}

	@Test
	void StoreOwner_삭제_로그인X_UNAUTHORIZED() {
		SessionFilter sessionFilter = new SessionFilter();
		StoreOwnerStep.signUp_API(spec);

		StoreOwnerDeleteDocs docs = new StoreOwnerDeleteDocs(UserDeleteRequest.class);

		RestAssured.given(spec).log().all()
			.filter(docs.validationFilterInRestAssured(Identifier.STORE_OWNER_DELETE_401_UNAUTHORIZED))
			.filter(sessionFilter)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.body(docs.createRequest())
			.when()
			.delete("/store-owner")
			.then()
			.statusCode(HttpStatus.UNAUTHORIZED.value())
			.log().all().extract();
	}

}