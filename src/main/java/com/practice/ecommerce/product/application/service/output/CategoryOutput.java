package com.practice.ecommerce.product.application.service.output;

import com.practice.ecommerce.product.domain.Category;
import com.practice.ecommerce.product.infra.web.dto.CategoryRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryOutput {

	void register(Category category);


	void deleteByCategoryId(Long categoryId);

	Category getById(Long categoryId);
}
