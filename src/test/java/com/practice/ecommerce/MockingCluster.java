package com.practice.ecommerce;

import com.practice.ecommerce.product.application.service.ProductUsecase;
import com.practice.ecommerce.product.application.service.impl.ProductSearchService;
import com.practice.ecommerce.user.application.service.UserUsecase;
import org.springframework.boot.test.mock.mockito.MockBean;


public class MockingCluster {
	@MockBean
	protected UserUsecase userUsecase;
	@MockBean
	protected ProductUsecase productUsecase;
	@MockBean
	protected ProductSearchService productSearchService;
}
