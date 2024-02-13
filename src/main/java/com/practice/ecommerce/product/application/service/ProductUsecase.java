package com.practice.ecommerce.product.application.service;

import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductRegisterRequest;
import com.practice.ecommerce.product.infra.web.dto.ReviewDeleteRequest;
import com.practice.ecommerce.product.infra.web.dto.ReviewUpdateRequest;
import com.practice.ecommerce.product.infra.web.dto.ReviewRequest;

public interface ProductUsecase {
	void register(ProductRegisterRequest request, String accountId);

	ProductDetailResponse getProductDetail(Long Id);

	void registerReview(ReviewRequest request, String accountId);

	void updateReview(String accountId, ReviewUpdateRequest request);

	void deleteReview(String accountId, ReviewDeleteRequest request);

}
