package com.practice.ecommerce.docsUtils;

import com.practice.ecommerce.product.infra.web.dto.ReviewType;

public class VirtualReview {
	private static Double star = 4.5;
	private static String content = "옷이 너무 마음에 들어요";
	private static ReviewType reviewType = ReviewType.PRODUCT_REVIEW;

	public static Double getStar() {
		return star;
	}

	public static String getContent() {
		return content;
	}

	public static ReviewType getReviewType() {
		return reviewType;
	}

	public static void setStar(Double star) {
		VirtualReview.star = star;
	}

	public static void setContent(String content) {
		VirtualReview.content = content;
	}

	public static void setReviewType(ReviewType reviewType) {
		VirtualReview.reviewType = reviewType;
	}

	public static void clear() {
		star = 4.5;
		content = "옷이 너무 마음에 들어요";
		reviewType = ReviewType.PRODUCT_REVIEW;
	}
}
