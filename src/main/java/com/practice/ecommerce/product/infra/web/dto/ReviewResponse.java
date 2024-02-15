package com.practice.ecommerce.product.infra.web.dto;

import com.practice.ecommerce.config.DocsDescription;


public record ReviewResponse(@DocsDescription(value = "리뷰 ID", nullable = true) Long id,
							 @DocsDescription(value = "리뷰 내용", nullable = true) String content,
							 @DocsDescription(value = "별점", nullable = true) Double star,
							 @DocsDescription(value = "리뷰답글 수 defulat 0") Integer replyCount) {
}
