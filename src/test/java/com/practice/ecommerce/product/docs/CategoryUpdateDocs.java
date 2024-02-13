package com.practice.ecommerce.product.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualCategory;
import com.practice.ecommerce.product.infra.web.dto.CategoryRequest;

public class CategoryUpdateDocs extends Docs {

	@Override
	public String getIdentifier() {
		return Identifier.CATEGORY_UPDATE_200_OK;
	}

	@Override
	public String getDescription() {
		return "카테고리를 수정할 수 있습니다.";
	}

	@Override
	public String getSummary() {
		return "카테고리 수정";
	}

	@Override
	public Class<?> getRequestClass() {
		return CategoryRequest.class;
	}

	@Override
	public Class<?> getResponseClass() {
		return null;
	}

	@Override
	public Class<?> getListResponseClass() {
		return null;
	}

	public CategoryRequest createRequest() {
		return new CategoryRequest(VirtualCategory.getName());
	}
}
