package com.practice.ecommerce.product.infra.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.practice.ecommerce.AbstractContainerBaseTest;
import com.practice.ecommerce.product.application.service.impl.ProductSearchService;
import com.practice.ecommerce.product.docs.ProductSearchDocs;
import com.practice.ecommerce.product.infra.adapter.ProductQueryRepository;
import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import com.practice.ecommerce.product.infra.web.dto.ReviewResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;





@SpringBootTest
public class ProductSearchControllerCachingTest extends AbstractContainerBaseTest {

	@MockBean
	private ProductQueryRepository productQueryRepository;
	@Autowired
	private ProductSearchService productSearchService;
	private final ProductSearchDocs docs = new ProductSearchDocs();

	@Test
	void 상품_목록_조회는_캐싱된다(){
		//given
		ReviewResponse reviewResponse = new ReviewResponse(1L, "리뷰입니다.", 4.5, 5);
		when(productQueryRepository.getProducts(any())).thenReturn(Arrays.asList(
			new ProductResponse(1L, "2", 3, "4", List.of(reviewResponse)),
			new ProductResponse(2L, "2", 3, "4",List.of(reviewResponse)),
			new ProductResponse(3L, "2", 3, "4",List.of(reviewResponse))
		));

		// when
		IntStream.range(0, 10)
			.forEach(i -> productSearchService.getProducts(docs.createRequest()));
		//then
		verify(productQueryRepository, atMost(1)).getProducts(any());

	}


}