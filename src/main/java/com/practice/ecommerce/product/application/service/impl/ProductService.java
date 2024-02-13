package com.practice.ecommerce.product.application.service.impl;

import com.practice.ecommerce.product.application.service.ProductUsecase;
import com.practice.ecommerce.product.application.service.output.CategoryOutput;
import com.practice.ecommerce.product.application.service.output.ProductOutput;
import com.practice.ecommerce.product.application.service.output.ReviewOutput;
import com.practice.ecommerce.product.domain.Category;
import com.practice.ecommerce.product.domain.Product;
import com.practice.ecommerce.product.domain.Review;
import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductRegisterRequest;
import com.practice.ecommerce.product.infra.web.dto.ReviewDeleteRequest;
import com.practice.ecommerce.product.infra.web.dto.ReviewRequest;
import com.practice.ecommerce.product.infra.web.dto.ReviewUpdateRequest;
import com.practice.ecommerce.user.application.outport.UserOutport;
import com.practice.ecommerce.user.domain.User;
import com.practice.ecommerce.user.domain.vo.LoginId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService implements ProductUsecase {

	private final CategoryOutput categoryOutput;
	private final ProductOutput productOutput;
	private final UserOutport userOutport;
	private final ReviewOutput reviewOutput;

	@Transactional
	public void register(ProductRegisterRequest request, String accountId) {

		Category category = categoryOutput.getById(request.categoryId());
		User storeOwner = userOutport.getUser(LoginId.create(accountId));

		Product product = Product.create(
			storeOwner,
			request.name(),
			request.explanation(),
			request.price(),
			request.stock(),
			category
		);

		productOutput.save(product);
	}

	@Override
	public ProductDetailResponse getProductDetail(Long id) {
		return productOutput.getById(id);
	}

	@Transactional
	@Override
	public void registerReview(ReviewRequest request, String accountId) {
		switch (request.reviewType()) {
			case PRODUCT_REVIEW -> registerProductReview(request,accountId);
			case SUB_REVIEW -> registerSubReview(request,accountId);
		}
	}
	private void registerProductReview(ReviewRequest request, String accountId) {
		Product product = productOutput.getProduct(request.parentId());
		User user = userOutport.getUser(LoginId.create(accountId));

		if(request.star() == null) {
			throw new IllegalArgumentException("별점은 필수입니다.");
		}

		Review review = Review.createProductReview(
			product,
			user,
			request.content(),
			request.star()
		);

		reviewOutput.save(review);
	}

	private void registerSubReview(ReviewRequest request, String accountId) {
		Review parent = reviewOutput.getById(request.parentId());
		User user = userOutport.getUser(LoginId.create(accountId));

		Review review = Review.createSubReview(
			parent,
			user,
			request.content()
		);

		reviewOutput.save(review);
	}

	@Transactional
	@Override
	public void updateReview(String accountId, ReviewUpdateRequest request) {
		Review review = reviewOutput.getById(request.reviewId());
		User user = userOutport.getUser(LoginId.create(accountId));
		review.isOwner(user.getId());
		review.updateContent(request.content());
	}

	@Transactional
	@Override
	public void deleteReview(String accountId, ReviewDeleteRequest request) {
		Long reviewId = request.reviewId();
		Review review = reviewOutput.getById(reviewId);
		User user = userOutport.getUser(LoginId.create(accountId));
		if(!review.isOwner(user.getId())) {
			throw new IllegalArgumentException("본인의 리뷰만 삭제할 수 있습니다.");
		}

		reviewOutput.deleteReview(reviewId);
	}

}
