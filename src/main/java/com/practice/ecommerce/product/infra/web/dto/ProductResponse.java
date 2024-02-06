package com.practice.ecommerce.product.infra.web.dto;

import com.querydsl.core.annotations.QueryProjection;

public record ProductResponse(
	Long id,
	String name,
	int price,
	String categoryName
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
