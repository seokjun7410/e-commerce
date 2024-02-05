package com.practice.ecommerce.product.assertcluster;

import static org.assertj.core.api.Assertions.assertThat;

import com.practice.ecommerce.docsUtils.VirtualCategory;
import com.practice.ecommerce.docsUtils.VirtualProduct;
import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;

public class AssertCluster {

	public static void productDetailResponse(ProductDetailResponse response) {
		assertThat(response.name()).isEqualTo(VirtualProduct.getName());
		assertThat(response.stock()).isEqualTo(VirtualProduct.getStock());
		assertThat(response.price()).isEqualTo(VirtualProduct.getPrice());
		assertThat(response.explanation()).isEqualTo(VirtualProduct.getExplanation());
		assertThat(response.categoryName()).isEqualTo(VirtualCategory.getName());
	}
}
