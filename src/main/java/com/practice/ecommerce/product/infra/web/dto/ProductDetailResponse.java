package com.practice.ecommerce.product.infra.web.dto;

import com.practice.ecommerce.config.DocsDescription;
import com.practice.ecommerce.product.domain.Product;

public record ProductDetailResponse(
	@DocsDescription("이름") String name,
	@DocsDescription("가격") Integer price,
	@DocsDescription("재고 수") int stock,
	@DocsDescription("상품 설명") String explanation,
	@DocsDescription("카테고리 분류") String categoryName
	) {

	public static ProductDetailResponse from(Product product) {
		return new ProductDetailResponse(
			product.getName(),
			product.getPrice(),
			product.getStock(),
			product.getExplanation(),
			product.getCategory().getName()
		);
	}
}
