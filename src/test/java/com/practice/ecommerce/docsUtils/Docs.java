package com.practice.ecommerce.docsUtils;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import org.springframework.restdocs.headers.RequestHeadersSnippet;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.restassured.RestDocumentationFilter;

public abstract class Docs extends BaseDocs {

	private RestAssuredRestDocumentationBuilder builder;

	public Docs() {
		if (getRequestClass() != null && getResponseClass() != null) {
			builder = new RestAssuredRestDocumentationBuilder(getIdentifier(), getDescription(),
				getSummary())
				.requestHeader(super.globalDefaultHeaderSpec())
				.addRequest(getRequestClass())
				.addResponse(getResponseClass());
		} else if (getResponseClass() != null) {
			builder = new RestAssuredRestDocumentationBuilder(getIdentifier(), getDescription(),
				getSummary())
				.requestHeader(super.globalDefaultHeaderSpec())
				.addResponse(getResponseClass());
		} else if (getRequestClass() != null) {
			builder = new RestAssuredRestDocumentationBuilder(getIdentifier(), getDescription(),
				getSummary())
				.requestHeader(super.globalDefaultHeaderSpec())
				.addRequest(getRequestClass());
		} else {
			builder = new RestAssuredRestDocumentationBuilder(getIdentifier(), getDescription(),
				getSummary())
				.requestHeader(super.globalDefaultHeaderSpec());
		}
	}

	public abstract String getIdentifier();

	public abstract String getDescription();

	public abstract String getSummary();

	public abstract Class<?> getRequestClass();

	public abstract Class<?> getResponseClass();


	public RestDocumentationFilter successFilter() {
		return builder.build();
	}

	public RestDocumentationFilter validationFilterInRestAssured(String identifier) {
		return document(identifier, getDescription(), getSummary(),
			super.defaultExceptionResponseSpec());
	}

	public RestDocumentationResultHandler validationFilterInMockMvc(String identifier) {
		return MockMvcRestDocumentationWrapper.document(identifier, getDescription(), getSummary(),
			super.defaultExceptionResponseSpec());
	}

	public RestAssuredRestDocumentationBuilder docsBuilder() {
		return builder;
	}

	public RequestHeadersSnippet defaultHeader() {
		return super.globalDefaultHeaderSpec();
	}

	public ParamBuilder paramBuilder() {
		return new ParamBuilder();
	}

}