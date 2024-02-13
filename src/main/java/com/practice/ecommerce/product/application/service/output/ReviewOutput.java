package com.practice.ecommerce.product.application.service.output;

import com.practice.ecommerce.product.domain.Review;

public interface ReviewOutput {

	Review getById(Long reviewId);

	void save(Review review);

	void deleteReview(Long reviewId);
}
