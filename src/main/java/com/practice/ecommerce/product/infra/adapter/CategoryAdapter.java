package com.practice.ecommerce.product.infra.adapter;

import com.practice.ecommerce.product.application.service.output.CategoryOutput;
import com.practice.ecommerce.product.domain.Category;
import com.practice.ecommerce.product.infra.web.dto.CategoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CategoryAdapter implements CategoryOutput {

	private final CategoryRepository categoryRepository;
	@Override
	public void register(String name) {
		Category category = Category.create(name);
		categoryRepository.save(category);
	}

	@Override
	public void update(Long categoryId, CategoryRequest request) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow(()-> {
				log.error("JPA ERROR, 존재하지 않는 카테고리 id 입니다. ID : {}",categoryId);
				return new RuntimeException("존재하지 않는 카테고리 id 입니다.");
			});
		category.update(request.name());
	}

	@Override
	public void deleteByCategoryId(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}
}
