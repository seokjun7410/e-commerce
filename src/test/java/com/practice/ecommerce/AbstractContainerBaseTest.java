package com.practice.ecommerce;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;

public class AbstractContainerBaseTest {

	private static final String REDIS_IMAGE = "redis:6-alpine";
	private static final String MYSQL_IMAGE = "mysql:8";
	private static final GenericContainer REDIS_CONTAINER;
	private static final MySQLContainer MYSQL_CONTAINER;

	static {
		REDIS_CONTAINER = new GenericContainer<>(REDIS_IMAGE)
			.withExposedPorts(6379)
			.withReuse(true);
		MYSQL_CONTAINER = new MySQLContainer(MYSQL_IMAGE);
		MYSQL_CONTAINER.start();
		REDIS_CONTAINER.start();
	}

	@DynamicPropertySource
	public static void overrideProps(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
		registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);
		registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
		registry.add("spring.redis.host", REDIS_CONTAINER::getHost);
		registry.add("spring.redis.port",
			() -> String.valueOf(REDIS_CONTAINER.getMappedPort(6379)));
	}
}
