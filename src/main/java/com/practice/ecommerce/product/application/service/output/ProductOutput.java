package com.practice.ecommerce.product.application.service.output;

import com.practice.ecommerce.product.domain.Product;
import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;

public interface ProductOutput {

	void save(Product product);

	ProductDetailResponse getById(Long id);
}
