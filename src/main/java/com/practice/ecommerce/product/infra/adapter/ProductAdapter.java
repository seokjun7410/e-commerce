package com.practice.ecommerce.product.infra.adapter;

import com.practice.ecommerce.product.application.service.output.ProductOutput;
import com.practice.ecommerce.product.domain.Product;
import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductSearchRequest;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductAdapter implements ProductOutput {

	private final ProductRepository productRepository;
	private final ProductQueryRepository productQueryRepository;

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public ProductDetailResponse getById(Long id) {
		Product product = getProduct(id);
		return ProductDetailResponse.from(product);
	}

	@Override
	public List<ProductResponse> getProducts(ProductSearchRequest request) {
		return productQueryRepository.getProducts(request);
	}

	@Override
	public Product getProduct(Long productId) {
		return productRepository.findById(productId)
			.orElseThrow(()->{
				log.error("product 상세 조회 에러 존재하지 않는 productID:{}", productId);
				return new EntityNotFoundException("존재하지 않는 상품입니다.");
			});
	}
}
