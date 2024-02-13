package com.practice.ecommerce.product.infra.web.dto;

import com.practice.ecommerce.config.DocsDescription;
import com.querydsl.core.annotations.QueryProjection;

public record ProductResponse(
	@DocsDescription(value = "상품 ID") Long id,
	@DocsDescription(value = "상품 이름") String name,
	@DocsDescription(value = "상품 가격") int price,
	@DocsDescription(value = "카테고리 이름") String categoryName
)
{

	@QueryProjection
	public ProductResponse(Long id, String name, int price, String categoryName) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.categoryName = categoryName;
	}
}
