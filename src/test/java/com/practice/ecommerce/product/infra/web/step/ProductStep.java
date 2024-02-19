package com.practice.ecommerce.product.infra.web.step;

import com.practice.ecommerce.JsonPathExpression;
import com.practice.ecommerce.product.docs.ProductGetDocs;
import com.practice.ecommerce.product.docs.ProductRegisterDocs;
import com.practice.ecommerce.product.docs.ProductSearchDocs;
import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.restassured.RestDocumentationFilter;

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

	public static ProductDetailResponse product_get_API(RequestSpecification spec) {
		ProductGetDocs docs = new ProductGetDocs();
		RestDocumentationFilter filter = docs.docsBuilder()
			.addPathVariable(docs.paramBuilder()
				.param("id", "상품 ID")
				.buildPathParameters())
			.build();

		final var result = RestAssured.given(spec).log().all()
			.filter(filter)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.when()
			.pathParam("id",1)
			.get("/product/{id}")
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();

		return result.response().jsonPath().getObject(JsonPathExpression.response, ProductDetailResponse.class);
	}

	public static List<ProductResponse> product_search_API(RequestSpecification spec) {
		ProductSearchDocs docs = new ProductSearchDocs();
		final var result = RestAssured.given(spec).log().all()
			.filter(docs.successFilter())
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.body(docs.createRequest())
			.when()
			.post("/product/search")
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();

		return result.response().jsonPath().getList(JsonPathExpression.response, ProductResponse.class);
	}
}
