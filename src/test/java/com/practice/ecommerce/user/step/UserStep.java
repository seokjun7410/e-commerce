package com.practice.ecommerce.user.step;

import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.docs.StoreOwnerDeleteDocs;
import com.practice.ecommerce.user.docs.StoreOwnerLogoutDocs;
import com.practice.ecommerce.user.docs.StoreOwnerMyInfoDocs;
import com.practice.ecommerce.user.docs.StoreOwnerSignInDocs;
import com.practice.ecommerce.user.docs.StoreOwnerSignUpDocs;
import com.practice.ecommerce.user.docs.StoreOwnerUpdatePasswordDocs;
import com.practice.ecommerce.user.docs.UserLoginDocs;
import com.practice.ecommerce.user.infra.web.dto.StoreOwnerRegisterRequest;
import com.practice.ecommerce.user.infra.web.dto.UserResponse;
import com.practice.ecommerce.user.infra.web.dto.UserLoginRequest;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.restassured.RestDocumentationFilter;

public class UserStep {

	public static void stoerOwner_signUp_API(RequestSpecification spec) {

		StoreOwnerRegisterRequest request = StoreOwnerSignUpDocs.Request.create();
		StoreOwnerSignUpDocs storeOwnerSignUpDocs = new StoreOwnerSignUpDocs();

		final var response = RestAssured.given(spec).log().all()
			.filter(storeOwnerSignUpDocs.successFilter())
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.body(request)
			.when()
			.post("/store-owner/sign-up")
			.then()
			.statusCode(HttpStatus.CREATED.value())
			.log().all().extract();
	}

	public static void admin_signUp_API(RequestSpecification spec) {

		StoreOwnerRegisterRequest request = StoreOwnerSignUpDocs.Request.create();
		StoreOwnerSignUpDocs storeOwnerSignUpDocs = new StoreOwnerSignUpDocs();

		final var response = RestAssured.given(spec).log().all()
			.filter(storeOwnerSignUpDocs.successFilter())
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.body(request)
			.when()
			.post("/admin/sign-up")
			.then()
			.statusCode(HttpStatus.CREATED.value())
			.log().all().extract();
	}

	public static void login_API(RequestSpecification spec, SessionFilter sessionFilter) {
		UserLoginRequest request = UserLoginDocs.Request.create();

		StoreOwnerSignInDocs docs = new StoreOwnerSignInDocs();
		RestDocumentationFilter docsFilter = docs.docsBuilder()
			.addQueryParam(
				docs.paramBuilder()
					.param("userType", UserType.class)
					.buildQueryParameters()
			)
			.build();

		final var response = RestAssured.given(spec).log().all()
			.filter(docsFilter)
			.filter(sessionFilter)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.queryParam("userType", UserType.STORE_OWNER.name())
			.body(request)
			.when()
			.post("/store-owner/sign-in?userType=" + UserType.STORE_OWNER.name())
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();
	}

	public static void login_API(RequestSpecification spec, SessionFilter sessionFilter,
		UserType userType) {
		UserLoginRequest request = UserLoginDocs.Request.create();

		StoreOwnerSignInDocs storeOwnerSignInDocs = new StoreOwnerSignInDocs();
		final var response = RestAssured.given(spec).log().all()
			.filter(storeOwnerSignInDocs.successFilter())
			.filter(sessionFilter)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.body(request)
			.when()
			.post("/store-owner/sign-in?userType=" + userType.name())
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();
	}

	public static UserResponse MyInfo_API(RequestSpecification spec, SessionFilter sessionFilter) {

		StoreOwnerMyInfoDocs storeOwnerMyInfoDocs = new StoreOwnerMyInfoDocs();

		final var result = RestAssured.given(spec).log().all()
			.filter(storeOwnerMyInfoDocs.successFilter())
			.filter(sessionFilter)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.when()
			.get("/store-owner/my-info")
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();

		return result.response().jsonPath().getObject("response", UserResponse.class);
	}

	public static void logout_API(RequestSpecification spec, SessionFilter sessionFilter) {

		StoreOwnerLogoutDocs storeOwnerLogoutDocs = new StoreOwnerLogoutDocs();

		final var resposne = RestAssured.given(spec).log().all()
			.filter(storeOwnerLogoutDocs.successFilter())
			.filter(sessionFilter)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.when()
			.get("/store-owner/logout")
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();
	}

	public static void updatePassword_API(RequestSpecification spec, SessionFilter sessionFilter,
		String newPassword) {

		StoreOwnerUpdatePasswordDocs docs = new StoreOwnerUpdatePasswordDocs();

		RestAssured.given(spec).log().all()
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.filter(docs.successFilter())
			.filter(sessionFilter)
			.body(docs.requestCreate(newPassword))
			.when()
			.patch("/store-owner/password")
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();
	}


	public static void delete_API(RequestSpecification spec, SessionFilter sessionFilter) {

		StoreOwnerDeleteDocs docs = new StoreOwnerDeleteDocs();

		RestAssured.given(spec).log().all()
			.filter(docs.successFilter())
			.filter(sessionFilter)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.body(docs.createRequest())
			.when()
			.delete("/store-owner")
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();
	}
}
