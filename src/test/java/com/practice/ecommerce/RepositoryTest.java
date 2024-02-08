package com.practice.ecommerce;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ComponentScan(basePackages = "com.practice.ecommerce")

public class RepositoryTest extends AbstractContainerBaseTest{

	@Autowired
	private CleanUp cleanUp;

	@AfterEach
	void tearDown() {
		cleanUp.tearDown();
	}

}
