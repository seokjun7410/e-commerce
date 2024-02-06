package com.practice.ecommerce.product.application.service.output;

import com.practice.ecommerce.product.domain.Product;
import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductSearchRequest;
import java.util.List;

public interface ProductOutput {

	void save(Product product);

	ProductDetailResponse getById(Long id);

	List<ProductResponse> getProducts(ProductSearchRequest request);
}
