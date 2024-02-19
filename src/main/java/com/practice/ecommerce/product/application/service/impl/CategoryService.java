package com.practice.ecommerce.product.application.service.impl;

import com.practice.ecommerce.product.application.service.CategoryUseCase;
import com.practice.ecommerce.product.application.service.output.CategoryOutput;
import com.practice.ecommerce.product.domain.Category;
import com.practice.ecommerce.product.infra.web.dto.CategoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CategoryService implements CategoryUseCase {


	private final CategoryOutput categoryOutput;

	@Transactional
	@Override
	public void register(CategoryRequest request) {
		Category category = Category.create(request.name());
		categoryOutput.register(category);
	}

	@Override
	@Transactional
	public void update(Long categoryId, CategoryRequest request) {
		Category category = categoryOutput.getById(categoryId);
		category.update(request.name());
	}

	@Override
	public void delete(Long categoryId) {
		categoryOutput.deleteByCategoryId(categoryId);
	}
}
