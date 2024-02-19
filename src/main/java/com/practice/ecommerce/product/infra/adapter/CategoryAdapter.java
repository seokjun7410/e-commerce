package com.practice.ecommerce.product.infra.adapter;

import com.practice.ecommerce.common.excpetion.CustomException;
import com.practice.ecommerce.common.excpetion.ErrorCode;
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
	public void register(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void deleteByCategoryId(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}

	@Override
	public Category getById(Long categoryId) {
		return categoryRepository.findById(categoryId)
			.orElseThrow(()->{
				log.error("JPA ERROR, 조회 실패. 존재하지 않는 카테고리 id 입니다. ID : {}",categoryId);
				return new CustomException(ErrorCode.CATEGORY_IS_NOT_FOUND.appendId(categoryId));
			});
	}
}
