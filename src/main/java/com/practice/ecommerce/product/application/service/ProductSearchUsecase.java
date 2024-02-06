package com.practice.ecommerce.product.application.service;

import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductSearchRequest;
import java.util.List;

public interface ProductSearchUsecase {

	List<ProductResponse> getProducts(ProductSearchRequest request);
}
