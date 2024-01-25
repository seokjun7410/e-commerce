package com.practice.ecommerce.product.infra.web.step;

import com.practice.ecommerce.product.docs.CategoryDeleteDocs;
import com.practice.ecommerce.product.docs.CategoryRegisterDocs;
import com.practice.ecommerce.product.docs.CategoryUpdateDocs;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.restassured.RestDocumentationFilter;

public class CategoryStep {

	public static void register_API(RequestSpecification spec, SessionFilter sessionFilter) {

		CategoryRegisterDocs docs = new CategoryRegisterDocs();

		RestAssured.given(spec).log().all()
			.filter(sessionFilter)
			.filter(docs.successFilter())
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(docs.createRequest())
			.when()
			.post("/categories")
			.then()
			.statusCode(HttpStatus.CREATED.value())
			.log().all().extract();
	}

	public static void update_API(RequestSpecification spec, SessionFilter sessionFilter) {
		String categoryId = "1";

		CategoryUpdateDocs docs = new CategoryUpdateDocs();
		RestDocumentationFilter docsFilter = docs.docsBuilder()
			.addPathVariable(docs.paramBuilder()
				.param("categoryId","카테고리 Id")
				.buildPathParameters())
			.build();

		RestAssured.given(spec).log().all()
			.filter(sessionFilter)
			.filter(docsFilter)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(docs.createRequest())
			.when()
			.patch("/categories/{categoryId}",categoryId)
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();
	}

	public static void delete_API(RequestSpecification spec, SessionFilter sessionFilter) {
		CategoryDeleteDocs docs = new CategoryDeleteDocs();
		String categoryId = "1";

		RestDocumentationFilter docsFilter = docs.docsBuilder()
			.addPathVariable(docs.paramBuilder()
				.param("categoryId","카테고리 Id")
				.buildPathParameters())
			.build();

		RestAssured.given(spec).log().all()
			.filter(sessionFilter)
			.filter(docs.successFilter())
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.when()
			.delete("/categories/{categoryId}",categoryId)
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();
	}
}
