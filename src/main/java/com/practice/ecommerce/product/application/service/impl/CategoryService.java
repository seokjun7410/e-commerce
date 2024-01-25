package com.practice.ecommerce.product.application.service.impl;

import com.practice.ecommerce.product.application.service.CategoryUseCase;
import com.practice.ecommerce.product.application.service.output.CategoryOutput;
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

	@Override
	public void register(CategoryRequest request) {
		try {
			categoryOutput.register(request.name());
		} catch (Exception e) {
			log.error("카테고리 등록 login=null ERROR : {}", request);
			throw new RuntimeException("카테고리 등록 에러");
		}
	}

	@Override
	public void update(Long categoryId, CategoryRequest request) {
		try {
			categoryOutput.update(categoryId, request);
		} catch (Exception e) {
			log.error("카테고리 수정 에러 {} ERROR : {}", request, e.getMessage());
		}
	}

	@Override
	public void delete(Long categoryId) {
		try {
			categoryOutput.deleteByCategoryId(categoryId);
		} catch (Exception e) {
			log.error("카테고리 삭제 에러 categoryId:{} ERROR : {}", categoryId, e.getMessage());
		}
	}
}
