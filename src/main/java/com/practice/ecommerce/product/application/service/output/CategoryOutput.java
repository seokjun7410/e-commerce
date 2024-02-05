package com.practice.ecommerce.product.application.service.output;

import com.practice.ecommerce.product.domain.Category;
import com.practice.ecommerce.product.infra.web.dto.CategoryRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryOutput {

	void register(String name);

	void update(Long categoryId, CategoryRequest categoryRequest);

	void deleteByCategoryId(Long categoryId);

	Category getById(Long categoryId);
}
