package com.practice.ecommerce.product.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualProduct;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.product.infra.web.dto.CategoryDTO.SortStatus;
import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductSearchRequest;

public class ProductSearchDocs extends Docs {

	@Override
	public String getIdentifier() {
		return Identifier.PRODUCT_GET_LIST_200_OK;
	}

	@Override
	public String getDescription() {
		return "상품목록을 조회할 수 있다. 캐싱이 적용되어 있습니다.";
	}

	@Override
	public String getSummary() {
		return "상품목록 조회";
	}

	@Override
	public Class<?> getRequestClass() {
		return ProductSearchRequest.class;
	}

	@Override
	public Class<?> getResponseClass() {
		return ProductResponse.class;
	}

	public ProductSearchRequest createRequest() {
		return new ProductSearchRequest(
			VirtualProduct.getName(),
			VirtualProduct.getCategoryId(),
			VirtualStoreOwner.getNickName(),
			SortStatus.OLDEST
		);
	}
}
