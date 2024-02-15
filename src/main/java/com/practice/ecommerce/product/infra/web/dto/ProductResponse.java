package com.practice.ecommerce.product.infra.web.dto;

import com.practice.ecommerce.config.DocsDescription;
import java.util.List;

public record ProductResponse(
	@DocsDescription(value = "상품 ID") Long id,
	@DocsDescription(value = "상품 이름") String name,
	@DocsDescription(value = "상품 가격") int price,
	@DocsDescription(value = "카테고리 이름") String categoryName,
	@DocsDescription(value = "리뷰응답") List<ReviewResponse> reviewResponse
)
{ }
