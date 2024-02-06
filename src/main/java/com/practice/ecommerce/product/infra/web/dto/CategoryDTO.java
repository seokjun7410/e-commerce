package com.practice.ecommerce.product.infra.web.dto;

import static com.practice.ecommerce.product.domain.QProduct.product;

import com.querydsl.core.types.OrderSpecifier;

public record CategoryDTO(
	int id,
	String name,
	SortStatus sortStatus,
	int searchCount,
	int pagingStartOffset
) {

	public enum SortStatus {
		NEWEST, OLDEST, PRICE_HIGH, PRICE_LOW;

		public OrderSpecifier<?> getOrderSpecifier() {
			return switch (this) {
				case PRICE_HIGH -> product.price.desc();
				case PRICE_LOW -> product.price.asc();
				case OLDEST -> product.createdDate.asc();
				case NEWEST -> product.createdDate.desc();
			};
		}
	}
}
