package com.practice.ecommerce.product.infra.adapter;

import com.practice.ecommerce.product.application.service.output.ProductOutput;
import com.practice.ecommerce.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductAdapter implements ProductOutput {

	private final ProductRepository productRepository;

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}
}
