package com.practice.ecommerce.product.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualProduct;
import com.practice.ecommerce.product.infra.web.ProductRegisterRequest;

public class ProductRegisterDocs extends Docs {

	@Override
	public String getIdentifier() {
		return Identifier.PRODUCT_REGISTER_200_OK;
	}

	@Override
	public String getDescription() {
		return "상품을 등록할 수 있다.";
	}

	@Override
	public String getSummary() {
		return "상품 등록";
	}

	@Override
	public Class<?> getRequestClass() {
		return ProductRegisterRequest.class;
	}

	@Override
	public Class<?> getResponseClass() {
		return null;
	}

	public ProductRegisterRequest createRequest() {
		return new ProductRegisterRequest(
			VirtualProduct.getName(),
			VirtualProduct.getExplanation(),
			VirtualProduct.getPrice(),
			VirtualProduct.getStock(),
			VirtualProduct.getCategoryId()
		);
	}
}
