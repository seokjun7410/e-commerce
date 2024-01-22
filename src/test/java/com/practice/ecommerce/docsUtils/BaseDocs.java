package com.practice.ecommerce.docsUtils;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.headers.RequestHeadersSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

public abstract class BaseDocs {

	protected RequestHeadersSnippet globalDefaultHeaderSpec() {
		return requestHeaders(
			headerWithName(HttpHeaders.CONTENT_TYPE).description("Content Type Header"));
	}

	protected ResponseFieldsSnippet defaultExceptionResponseSpec() {
		return responseFields(fieldWithPath("message").description("오류에 대한 메시지"));
	}

	protected String convertForDescription(List<String> constraints) {
		if (constraints.isEmpty()) {
			return "";
		}
		if (constraints.size() == 1) {
			return "[" + constraints.get(0) + "]";
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < constraints.size() - 1; i++) {
			stringBuilder.append("[").append(constraints.get(i)).append("], ");
		}

		stringBuilder.append("[").append(constraints.get(constraints.size() - 1)).append("]");

		return stringBuilder.toString();
	}
}