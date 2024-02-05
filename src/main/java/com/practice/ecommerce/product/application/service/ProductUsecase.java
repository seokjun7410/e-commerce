package com.practice.ecommerce.product.application.service;

import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductRegisterRequest;

public interface ProductUsecase {
	void register(ProductRegisterRequest request, String accountId);

	ProductDetailResponse getProductDetail(Long Id);
}
