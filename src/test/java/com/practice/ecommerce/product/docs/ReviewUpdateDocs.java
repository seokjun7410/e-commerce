package com.practice.ecommerce.product.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualReview;
import com.practice.ecommerce.product.infra.web.dto.ReviewUpdateRequest;

public class ReviewUpdateDocs extends Docs {

	@Override
	public String getIdentifier() {
		return Identifier.REVIEW_UPDATE_200_OK;
	}

	@Override
	public String getDescription() {
		return "리뷰 및 서브리뷰의 코멘트를 업데이트할 수 있다.";
	}

	@Override
	public String getSummary() {
		return "리뷰/서브리뷰 업데이트";
	}

	@Override
	public Class<?> getRequestClass() {
		return ReviewUpdateRequest.class;
	}

	@Override
	public Class<?> getResponseClass() {
		return null;
	}

	@Override
	public Class<?> getListResponseClass() {
		return null;
	}

	public ReviewUpdateRequest createRequest(long reviewId) {
		return new ReviewUpdateRequest(
			reviewId,
			VirtualReview.getContent()
		);
	}
}
