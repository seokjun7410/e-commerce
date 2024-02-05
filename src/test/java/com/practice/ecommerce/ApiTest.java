package com.practice.ecommerce;

import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.product.infra.adapter.CategoryRepository;
import com.practice.ecommerce.product.infra.adapter.ProductRepository;
import com.practice.ecommerce.user.infra.adapter.UserRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.annotation.DirtiesContext;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public class ApiTest {

	protected RequestSpecification spec;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	@AfterEach
	void tearDown() {
		productRepository.deleteAll();
		categoryRepository.deleteAll();
		userRepository.deleteAll();
		VirtualStoreOwner.clear();
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