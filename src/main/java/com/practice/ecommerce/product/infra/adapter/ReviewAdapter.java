package com.practice.ecommerce.product.infra.adapter;

import com.practice.ecommerce.product.application.service.output.ReviewOutput;
import com.practice.ecommerce.product.domain.Review;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReviewAdapter implements ReviewOutput {

	private final ReviewRepository reviewRepository;


	@Override
	public Review getById(Long reviewId) {
		return reviewRepository.findById(reviewId)
			.orElseThrow(() -> {
				log.error("review not found");
				return new EntityNotFoundException("review not found");
			});
	}

	@Override
	public void save(Review review) {
		reviewRepository.save(review);
	}

	@Override
	public void deleteReview(Long reviewId) {
		reviewRepository.deleteById(reviewId);
	}
}
