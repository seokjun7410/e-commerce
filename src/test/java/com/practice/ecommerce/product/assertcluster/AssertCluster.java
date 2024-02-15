package com.practice.ecommerce.product.assertcluster;

import static org.assertj.core.api.Assertions.assertThat;

import com.practice.ecommerce.docsUtils.VirtualCategory;
import com.practice.ecommerce.docsUtils.VirtualProduct;
import com.practice.ecommerce.product.infra.web.dto.CategoryDTO.SortStatus;
import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import com.practice.ecommerce.product.infra.web.dto.ReviewResponse;
import java.util.List;

public class AssertCluster {

	public static void productDetailResponse(ProductDetailResponse response) {
		assertThat(response.name()).isEqualTo(VirtualProduct.getName());
		assertThat(response.stock()).isEqualTo(VirtualProduct.getStock());
		assertThat(response.price()).isEqualTo(VirtualProduct.getPrice());
		assertThat(response.explanation()).isEqualTo(VirtualProduct.getExplanation());
		assertThat(response.categoryName()).isEqualTo(VirtualCategory.getName());
	}

	public static void productResponse(List<ProductResponse> productResponses) {
		assertThat(productResponses).hasSize(3);
		assertThat(productResponses.get(0).id()).isNotNull();
		assertThat(productResponses.get(0).name()).isEqualTo(VirtualProduct.getName());
		assertThat(productResponses.get(0).categoryName()).isEqualTo(VirtualCategory.getName());
		assertThat(productResponses.get(0).price()).isEqualTo(VirtualProduct.getPrice());

		List<ReviewResponse> reviewResponse = productResponses.get(0).reviewResponse();
		assertThat(reviewResponse.size()).isEqualTo(3);
		assertThat(reviewResponse.get(0).content()).contains("옷이 너무 마음에 들어요");
		assertThat(reviewResponse.get(0).star()).isEqualTo(4.5);
		assertThat(reviewResponse.get(0).replyCount()).isEqualTo(3);
	}

	public static void productResponseSorting(List<ProductResponse> products, SortStatus sortStatus) {

	}
}
