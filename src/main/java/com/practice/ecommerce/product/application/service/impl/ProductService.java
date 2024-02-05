package com.practice.ecommerce.product.application.service.impl;

import com.practice.ecommerce.product.application.service.ProductUsecase;
import com.practice.ecommerce.product.application.service.output.CategoryOutput;
import com.practice.ecommerce.product.application.service.output.ProductOutput;
import com.practice.ecommerce.product.domain.Category;
import com.practice.ecommerce.product.domain.Product;
import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService implements ProductUsecase {

	private final CategoryOutput categoryOutput;
	private final ProductOutput productOutput;

	@Transactional
	public void register(ProductRegisterRequest request, String accountId) {

		Category category = categoryOutput.getById(request.categoryId());

		Product product = Product.create(
			request.name(),
			request.explanation(),
			request.price(),
			request.stock(),
			category
		);

		productOutput.save(product);
	}

	@Override
	public ProductDetailResponse getProductDetail(Long id) {
		return productOutput.getById(id);
	}
}
