package com.practice.ecommerce.product.infra.web.dto;

public record ReviewUpdateRequest(
	Long reviewId,
	String content
) { }
