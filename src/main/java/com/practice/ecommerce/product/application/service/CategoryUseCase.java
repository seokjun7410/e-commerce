package com.practice.ecommerce.product.application.service;

import com.practice.ecommerce.product.infra.web.dto.CategoryRequest;

public interface CategoryUseCase {
	void register(CategoryRequest categoryRequest);

	void update(Long categoryId, CategoryRequest categoryRequest);

	void delete(Long categoryId);
}
