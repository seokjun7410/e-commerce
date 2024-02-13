package com.practice.ecommerce.product.infra.web.dto;

import com.practice.ecommerce.config.DocsDescription;
import jakarta.validation.constraints.NotNull;

public record ReviewRequest(
	@DocsDescription("리뷰 또는 상품 ID") @NotNull
	Long parentId, //product Or Review
	@DocsDescription(value = "별점",nullable = true)
	Double star,
	@DocsDescription("리뷰 글") @NotNull
	String content,
	@DocsDescription("리뷰 타입") @NotNull
	ReviewType reviewType
) {

}
