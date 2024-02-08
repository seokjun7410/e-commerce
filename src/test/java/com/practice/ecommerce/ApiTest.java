package com.practice.ecommerce;

import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public class ApiTest extends AbstractContainerBaseTest{

	protected RequestSpecification spec;

	@Autowired
	private CleanUp cleanUp;
	@BeforeEach
	void tearDown() {
		cleanUp.tearDown();
	}

	@BeforeEach
	void setUp(RestDocumentationContextProvider provider) {
		this.spec = new RequestSpecBuilder().addFilter(documentationConfiguration(provider))
			.build();
		RestAssured.port = port;
	}

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}


}