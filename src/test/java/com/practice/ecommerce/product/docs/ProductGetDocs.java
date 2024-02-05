package com.practice.ecommerce.product.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;

public class ProductGetDocs extends Docs {

	@Override
	public String getIdentifier() {
		return Identifier.PRODUCT_GET_200_OK;
	}

	@Override
	public String getDescription() {
		return "상품을 조회할 수 있다.";
	}

	@Override
	public String getSummary() {
		return "상품 조회";
	}

	@Override
	public Class<?> getRequestClass() {
		return null;
	}

	@Override
	public Class<?> getResponseClass() {
		return ProductDetailResponse.class;
	}

}
