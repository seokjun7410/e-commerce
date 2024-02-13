package com.practice.ecommerce.product.infra.web.step;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.product.infra.web.dto.ReviewDeleteRequest;

public class ReviewDeleteDocs extends Docs {

	@Override
	public String getIdentifier() {
		return Identifier.REVIEW_DELETE_200_OK;
	}

	@Override
	public String getDescription() {
		return "리뷰 및 서브리뷰를 삭제할 수 있다.";
	}

	@Override
	public String getSummary() {
		return "리뷰/서브리뷰 삭제";
	}

	@Override
	public Class<?> getRequestClass() {
		return ReviewDeleteRequest.class;
	}

	@Override
	public Class<?> getResponseClass() {
		return null;
	}

	@Override
	public Class<?> getListResponseClass() {
		return null;
	}

	public ReviewDeleteRequest createRequest(long reviewId) {
		return new ReviewDeleteRequest(
			reviewId
		);
	}
}
