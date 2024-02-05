package com.practice.ecommerce.product.application.service;

import com.practice.ecommerce.product.infra.web.ProductRegisterRequest;

public interface ProductUsecase {
	void register(ProductRegisterRequest request, String accountId);
}
