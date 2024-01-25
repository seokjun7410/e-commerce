package com.practice.ecommerce.product.infra.web.step;

import com.practice.ecommerce.product.docs.CategoryRegisterDocs;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

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
}
