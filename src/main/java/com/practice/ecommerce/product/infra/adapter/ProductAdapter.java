package com.practice.ecommerce.product.infra.adapter;

import com.practice.ecommerce.product.application.service.output.ProductOutput;
import com.practice.ecommerce.product.domain.Product;
import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductAdapter implements ProductOutput {

	private final ProductRepository productRepository;

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public ProductDetailResponse getById(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> {
				log.error("product 상세 조회 에러 존재하지 않는 productID:{}", id);
				return new RuntimeException("product 조회 에러 productID");
			});
		return ProductDetailResponse.from(product);
	}
}
