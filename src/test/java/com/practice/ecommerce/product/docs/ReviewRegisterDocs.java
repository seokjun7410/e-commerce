package com.practice.ecommerce.product.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualReview;
import com.practice.ecommerce.product.infra.web.dto.ReviewRequest;

public class ReviewRegisterDocs extends Docs {

	@Override
	public String getIdentifier() {
		return Identifier.REVIEW_REGISTER_200_OK;
	}

	@Override
	public String getDescription() {
		return "상품리뷰 및 서브리뷰를 등록할 수 있다. \n 상품리뷰인 경우에만 별점 필수";
	}

	@Override
	public String getSummary() {
		return "리뷰/서브리뷰 등록";
	}

	@Override
	public Class<?> getRequestClass() {
		return ReviewRequest.class;
	}

	@Override
	public Class<?> getResponseClass() {
		return null;
	}

	@Override
	public Class<?> getListResponseClass() {
		return null;
	}

	public ReviewRequest createRequest(Long parentId) {
		return new ReviewRequest(
			parentId,
			VirtualReview.getStar(),
			VirtualReview.getContent(),
			VirtualReview.getReviewType()
		);
	}
}
