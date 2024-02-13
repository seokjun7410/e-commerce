package com.practice.ecommerce.product.infra.web.step;

import com.practice.ecommerce.product.docs.ReviewRegisterDocs;
import com.practice.ecommerce.product.docs.ReviewUpdateDocs;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ReviewStep {

	public static void register_API(RequestSpecification spec, SessionFilter storeOwnerSession) {

		ReviewRegisterDocs docs = new ReviewRegisterDocs();
		RestAssured.given(spec).log().all()
			.filter(docs.successFilter())
			.filter(storeOwnerSession)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.body(docs.createRequest(1L))
			.when()
			.post("/review")
			.then()
			.statusCode(HttpStatus.CREATED.value())
			.log().all().extract();
	}

	public static void update_API(RequestSpecification spec, SessionFilter storeOwnerSession) {

		ReviewUpdateDocs docs = new ReviewUpdateDocs();
		RestAssured.given(spec).log().all()
			.filter(docs.successFilter())
			.filter(storeOwnerSession)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.body(docs.createRequest(1L))
			.when()
			.patch("/review")
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();
	}

	public static void delete_API(RequestSpecification spec, SessionFilter storeOwnerSession) {
		ReviewDeleteDocs docs = new ReviewDeleteDocs();
		RestAssured.given(spec).log().all()
			.filter(docs.successFilter())
			.filter(storeOwnerSession)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.body(docs.createRequest(1L))
			.when()
			.delete("/review")
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();
	}
}
