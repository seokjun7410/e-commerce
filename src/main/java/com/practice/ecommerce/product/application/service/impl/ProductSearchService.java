package com.practice.ecommerce.product.application.service.impl;

import com.practice.ecommerce.product.application.service.ProductSearchUsecase;
import com.practice.ecommerce.product.application.service.output.ProductOutput;
import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductSearchRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductSearchService implements ProductSearchUsecase {

	private final ProductOutput productOutput;

	@Cacheable(
		value = "getProducts",
		key = "'getProducts'+#request?.productName() + #request?.categoryId()")
	@Override
	public List<ProductResponse> getProducts(ProductSearchRequest request) {
		return productOutput.getProducts(request);
	}
}
