package com.practice.ecommerce.user.infra.web.validation;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.ecommerce.JsonPathExpression;
import com.practice.ecommerce.MockingCluster;
import com.practice.ecommerce.common.excpetion.ErrorCode;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.application.service.UserUsecase;
import com.practice.ecommerce.user.docs.StoreOwnerSignUpDocs;
import com.practice.ecommerce.user.infra.web.StoreOwnerController;
import com.practice.ecommerce.user.infra.web.dto.StoreOwnerRegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


@WebMvcTest(StoreOwnerController.class)
@AutoConfigureRestDocs
@DisplayName("스토어 오너 - 회원가입API - 유효성 테스트")
public class StoreOwnerSignUpValidationTest extends MockingCluster {

	@Autowired
	MockMvc mockMvc;
	ObjectMapper objectMapper = new ObjectMapper();

	@AfterEach
	void tearDown() {
		VirtualStoreOwner.clear();
	}

	@Test
	public void 회원가입_유효성_검사_password() throws Exception {
		VirtualStoreOwner.setPassword(null);
		StoreOwnerRegisterRequest request = StoreOwnerSignUpDocs.Request.create();

		callController(request)
			.andExpect(jsonPath(JsonPathExpression.errorMessage).value(ErrorCode.BAD_REQUEST_BODY.getDetail()));
	}

	@Test
	public void 회원가입_유효성_검사_loginId() throws Exception {
		VirtualStoreOwner.setLoginId(null);
		StoreOwnerRegisterRequest request = StoreOwnerSignUpDocs.Request.create();

		callController(request)
			.andExpect(jsonPath(JsonPathExpression.errorMessage).value(ErrorCode.BAD_REQUEST_BODY.getDetail()));
	}

	@Test
	public void 회원가입_유효성_검사_address() throws Exception {
		VirtualStoreOwner.setAddress(null);
		StoreOwnerRegisterRequest request = StoreOwnerSignUpDocs.Request.create();

		callController(request)
			.andExpect(jsonPath(JsonPathExpression.errorMessage).value(ErrorCode.BAD_REQUEST_BODY.getDetail()));
	}

	@Test
	public void 회원가입_유효성_검사_nickName() throws Exception {
		VirtualStoreOwner.setNickName(null);
		StoreOwnerRegisterRequest request = StoreOwnerSignUpDocs.Request.create();

		callController(request)
			.andExpect(jsonPath(JsonPathExpression.errorMessage).value(ErrorCode.BAD_REQUEST_BODY.getDetail()));
	}

	private ResultActions callController(StoreOwnerRegisterRequest request) throws Exception {

		StoreOwnerSignUpDocs docs = new StoreOwnerSignUpDocs();

		return mockMvc.perform(RestDocumentationRequestBuilders.post("/store-owner/sign-up?userType="+ UserType.STORE_OWNER)
				.contentType(MediaType.APPLICATION_JSON_VALUE)  // Set Content-Type explicitly
				.content(objectMapper.writeValueAsString(request))
			)
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andDo(docs.validationFilterInMockMvc(Identifier.STORE_OWNER_SIGN_UP_400_BAD));
	}
}
