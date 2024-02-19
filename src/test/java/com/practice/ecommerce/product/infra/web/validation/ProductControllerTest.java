package com.practice.ecommerce.product.infra.web.validation;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.ecommerce.JsonPathExpression;
import com.practice.ecommerce.MockingCluster;
import com.practice.ecommerce.common.excpetion.ErrorCode;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualProduct;
import com.practice.ecommerce.product.docs.ProductRegisterDocs;
import com.practice.ecommerce.product.infra.web.ProductController;
import com.practice.ecommerce.product.infra.web.dto.ProductRegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ProductController.class)
@AutoConfigureRestDocs
public class ProductControllerTest extends MockingCluster {

	@Autowired
	MockMvc mockMvc;
	ObjectMapper objectMapper = new ObjectMapper();



	private ProductRegisterDocs docs = new ProductRegisterDocs();

	@AfterEach
	void tearDown() {
		VirtualProduct.clear();
	}

	@Test
	public void 상품등록_유효성_검사_Name() throws Exception {
		VirtualProduct.setName(null);

		ProductRegisterRequest request = docs.createRequest();
		callApi(request)
			.andExpect(jsonPath(JsonPathExpression.response).value(ErrorCode.BAD_REQUEST_BODY));

	}

	@Test
	public void 상품등록_유효성_검사_Explanation() throws Exception {
		VirtualProduct.setExplanation(null);

		ProductRegisterRequest request = docs.createRequest();
		callApi(request)
			.andExpect(jsonPath(JsonPathExpression.response).value(ErrorCode.BAD_REQUEST_BODY));

	}

	@Test
	public void 상품등록_유효성_검사_categoryId() throws Exception {
		VirtualProduct.setCategoryId(null);

		ProductRegisterRequest request = docs.createRequest();
		callApi(request).andExpect(jsonPath(JsonPathExpression.response).value(ErrorCode.BAD_REQUEST_BODY));

	}

	@Test
	public void 상품등록_유효성_검사_stock() throws Exception {
		VirtualProduct.setStock(null);

		ProductRegisterRequest request = docs.createRequest();
		callApi(request).andExpect(jsonPath(JsonPathExpression.response).value(ErrorCode.BAD_REQUEST_BODY));

	}

	@Test
	public void 상품등록_유효성_검사_price() throws Exception {
		VirtualProduct.setPrice(null);

		ProductRegisterRequest request = docs.createRequest();
		callApi(request).andExpect(jsonPath(JsonPathExpression.response).value(ErrorCode.BAD_REQUEST_BODY));
	}

	private ResultActions callApi(ProductRegisterRequest request) throws Exception {
		return mockMvc.perform(
				RestDocumentationRequestBuilders.post("/product")
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)  // Set Content-Type explicitly
					.content(objectMapper.writeValueAsString(request))
			)
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andDo(docs.validationFilterInMockMvc(Identifier.PRODUCT_REGISTER_400_BAD));
	}


}