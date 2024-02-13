package com.practice.ecommerce.product.infra.web.dto;

import com.practice.ecommerce.config.DocsDescription;
import com.practice.ecommerce.product.infra.web.dto.CategoryDTO.SortStatus;
import jakarta.validation.constraints.NotNull;

public record ProductSearchRequest(
	@DocsDescription(value = "상품이름",nullable = true) String productName,
	@DocsDescription(value = "카테고리ID",nullable = true) Long categoryId,
	@DocsDescription(value = "가맹점 이름",nullable = true) String storeOwnerName,
	@NotNull SortStatus sortStatus
)
{
	@Override
	public String productName() {
		return productName;
	}
}
