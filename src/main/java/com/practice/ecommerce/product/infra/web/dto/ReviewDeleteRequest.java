package com.practice.ecommerce.product.infra.web.dto;

import com.practice.ecommerce.config.DocsDescription;

public record ReviewDeleteRequest(
	@DocsDescription("삭제할 리뷰 ID") Long reviewId
) {

}
