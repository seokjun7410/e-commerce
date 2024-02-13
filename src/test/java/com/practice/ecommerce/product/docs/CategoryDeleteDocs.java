package com.practice.ecommerce.product.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;

public class CategoryDeleteDocs extends Docs {

	@Override
	public String getIdentifier() {
		return Identifier.CATEGORY_DELETE_200_OK;
	}

	@Override
	public String getDescription() {
		return "카테고리를 삭제할 수 있습니다.";
	}

	@Override
	public String getSummary() {
		return "카테고리 삭제";
	}

	@Override
	public Class<?> getRequestClass() {
		return null;
	}

	@Override
	public Class<?> getResponseClass() {
		return null;
	}

	@Override
	public Class<?> getListResponseClass() {
		return null;
	}
}
