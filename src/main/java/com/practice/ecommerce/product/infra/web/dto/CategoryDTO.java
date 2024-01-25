package com.practice.ecommerce.product.infra.web.dto;

public record CategoryDTO(
	int id,
	String name,
	SortStatus sortStatus,
	int searchCount,
	int pagingStartOffset
) {

	public enum SortStatus {
		CATEGORIES, NEWEST, OLDEST, PRICE_HIGH, PRICE_LOW
	}
}
