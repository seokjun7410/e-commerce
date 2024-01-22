package com.practice.ecommerce.user.step;

import com.practice.ecommerce.user.docs.StoreOwnerDeleteDocs;
import com.practice.ecommerce.user.docs.StoreOwnerLogoutDocs;
import com.practice.ecommerce.user.docs.StoreOwnerMyInfoDocs;
import com.practice.ecommerce.user.docs.StoreOwnerSignInDocs;
import com.practice.ecommerce.user.docs.StoreOwnerSignUpDocs;
import com.practice.ecommerce.user.docs.StoreOwnerUpdatePasswordDocs;
import com.practice.ecommerce.user.docs.UserLoginDocs;
import com.practice.ecommerce.user.infra.web.dto.StoreOwnerRegisterRequest;
import com.practice.ecommerce.user.infra.web.dto.UserDeleteRequest;
import com.practice.ecommerce.user.infra.web.dto.UserDto;
import com.practice.ecommerce.user.infra.web.dto.UserLoginRequest;
import com.practice.ecommerce.user.infra.web.dto.UserPasswordUpdateRequest;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class StoreOwnerStep {

	public static void signUp_API(RequestSpecification spec) {

		StoreOwnerRegisterRequest request = StoreOwnerSignUpDocs.Request.create();
		StoreOwnerSignUpDocs storeOwnerSignUpDocs = new StoreOwnerSignUpDocs(
			StoreOwnerRegisterRequest.class);

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

	public static void login_API(RequestSpecification spec, SessionFilter sessionFilter) {
		UserLoginRequest request = UserLoginDocs.Request.create();

		StoreOwnerSignInDocs storeOwnerSignInDocs = new StoreOwnerSignInDocs(
			UserLoginRequest.class);
		final var response = RestAssured.given(spec).log().all()
			.filter(storeOwnerSignInDocs.successFilter())
			.filter(sessionFilter)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.body(request)
			.when()
			.post("/store-owner/sign-in")
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();
	}

	public static UserDto MyInfo_API(RequestSpecification spec, SessionFilter sessionFilter) {

		StoreOwnerMyInfoDocs storeOwnerMyInfoDocs = new StoreOwnerMyInfoDocs(null);

		final var result = RestAssured.given(spec).log().all()
			.filter(storeOwnerMyInfoDocs.getFilter())
			.filter(sessionFilter)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.when()
			.get("/store-owner/my-info")
			.then()
			.statusCode(HttpStatus.OK.value())
			.log().all().extract();

		return result.response().jsonPath().getObject("", UserDto.class);
	}

	public static void logout_API(RequestSpecification spec, SessionFilter sessionFilter) {

		StoreOwnerLogoutDocs storeOwnerLogoutDocs = new StoreOwnerLogoutDocs(null);

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

	public static void updatePassword_API(RequestSpecification spec, SessionFilter sessionFilter,String newPassword) {

		StoreOwnerUpdatePasswordDocs docs = new StoreOwnerUpdatePasswordDocs(
			UserPasswordUpdateRequest.class);

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

		StoreOwnerDeleteDocs docs = new StoreOwnerDeleteDocs(UserDeleteRequest.class);

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
