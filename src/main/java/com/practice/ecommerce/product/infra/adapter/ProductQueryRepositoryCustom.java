package com.practice.ecommerce.product.infra.adapter;

import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductSearchRequest;
import java.util.List;

public interface ProductQueryRepositoryCustom {
	List<ProductResponse> getProducts(ProductSearchRequest request);
}
