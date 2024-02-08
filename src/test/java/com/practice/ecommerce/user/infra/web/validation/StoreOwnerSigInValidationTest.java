package com.practice.ecommerce.user.infra.web.validation;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.ecommerce.MockingCluster;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.docs.StoreOwnerSignInDocs;
import com.practice.ecommerce.user.docs.UserLoginDocs;
import com.practice.ecommerce.user.infra.web.StoreOwnerController;
import com.practice.ecommerce.user.infra.web.dto.UserLoginRequest;
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
@DisplayName("스토어 오너 - 로그인API - 유효성 테스트")
public class StoreOwnerSigInValidationTest extends MockingCluster {

	@Autowired
	MockMvc mockMvc;
	ObjectMapper objectMapper = new ObjectMapper();


	private StoreOwnerSignInDocs docs = new StoreOwnerSignInDocs();

	@AfterEach
	void tearDown() {
		VirtualStoreOwner.clear();
	}

	@Test
	public void 회원가입_유효성_검사_password() throws Exception {
		VirtualStoreOwner.setPassword(null);

		UserLoginRequest request = UserLoginDocs.Request.create();
		callController(request)
			.andExpect(jsonPath("message").value("잘못된 Body 입니다. 문서를 참고하여 올바른 데이터를 첨부해주세요."));
	}

	@Test
	public void 회원가입_유효성_검사_loginId() throws Exception {
		VirtualStoreOwner.setLoginId(null);

		UserLoginRequest request = UserLoginDocs.Request.create();
		callController(request)
			.andExpect(jsonPath("message").value("잘못된 Body 입니다. 문서를 참고하여 올바른 데이터를 첨부해주세요."));

	}

	private ResultActions callController(UserLoginRequest request) throws Exception {

		return mockMvc.perform(
				RestDocumentationRequestBuilders.post("/store-owner/sign-in?userType="+ UserType.STORE_OWNER)
					.contentType(MediaType.APPLICATION_JSON_VALUE)  // Set Content-Type explicitly
					.content(objectMapper.writeValueAsString(request))
			)
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andDo(docs.validationFilterInMockMvc(Identifier.STORE_OWNER_SIGN_IN_400_BAD));

	}
}
