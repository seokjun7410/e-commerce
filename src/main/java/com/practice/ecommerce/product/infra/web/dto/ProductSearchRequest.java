package com.practice.ecommerce.product.infra.web.dto;

import com.practice.ecommerce.product.infra.web.dto.CategoryDTO.SortStatus;
import jakarta.validation.constraints.NotNull;

public record ProductSearchRequest(
	String productName,
	Long categoryId,
	String storeOwnerName,
	@NotNull SortStatus sortStatus
)
{
	@Override
	public String productName() {
		return productName;
	}
}
