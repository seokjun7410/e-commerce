package com.practice.ecommerce.user.infra.web.validation;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.user.application.service.UserUsecase;
import com.practice.ecommerce.user.docs.StoreOwnerUpdatePasswordDocs;
import com.practice.ecommerce.user.infra.web.dto.UserPasswordUpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


@WebMvcTest
@AutoConfigureRestDocs
@DisplayName("스토어 오너 - 비밀번호 변경 - 유효성 테스트")
public class StoreOwnerUpdatePasswordValidationTest {

	@Autowired
	MockMvc mockMvc;
	ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private UserUsecase userUsecase;

	private StoreOwnerUpdatePasswordDocs docs = new StoreOwnerUpdatePasswordDocs(null);

	@AfterEach
	void tearDown() {
		VirtualStoreOwner.clear();
	}

	@Test
	public void 회원가입_유효성_검사_newPassword() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("id", VirtualStoreOwner.getLoginId());
		UserPasswordUpdateRequest request = StoreOwnerUpdatePasswordDocs.requestCreate(null);

		callController(request)
			.andExpect(jsonPath("message").value("잘못된 Body 입니다. 문서를 참고하여 올바른 데이터를 첨부해주세요."));
	}

	@Test
	public void 회원가입_유효성_검사_Password() throws Exception {
		VirtualStoreOwner.setPassword(null);
		UserPasswordUpdateRequest request = StoreOwnerUpdatePasswordDocs.requestCreate("newPassword");

		callController(request)
			.andExpect(jsonPath("message").value("잘못된 Body 입니다. 문서를 참고하여 올바른 데이터를 첨부해주세요."));
	}



	private ResultActions callController(UserPasswordUpdateRequest request) throws Exception {

		return mockMvc.perform(
				RestDocumentationRequestBuilders.patch("/store-owner/password")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(request))
			)
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andDo(docs.validationFilterInMockMvc(Identifier.STORE_OWNER_UPDATE_PASSWORD_400_BAD));

	}
}
