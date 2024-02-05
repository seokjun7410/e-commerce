package com.practice.ecommerce.product.infra.web.step;

import com.practice.ecommerce.product.docs.ProductRegisterDocs;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ProductStep {

	public static void product_register_API(RequestSpecification spec, SessionFilter sessionFilter) {
		ProductRegisterDocs docs = new ProductRegisterDocs();
		RestAssured.given(spec).log().all()
			.filter(docs.successFilter())
			.filter(sessionFilter)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.body(docs.createRequest())
			.when()
			.post("/product")
			.then()
			.statusCode(HttpStatus.CREATED.value())
			.log().all().extract();
	}
}
