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
import java.util.Arrays;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;





@SpringBootTest
public class ProductSearchControllerCachingTest extends AbstractContainerBaseTest {

	@SpyBean
	private ProductSearchService productSearchService;
	@MockBean
	private ProductQueryRepository productQueryRepository;
	private final ProductSearchDocs docs = new ProductSearchDocs();

	@Test
	void 상품_목록_조회는_캐싱된다(){
		//given
		when(productQueryRepository.getProducts(any())).thenReturn(Arrays.asList(
			new ProductResponse(1L, "2", 3, "4"),
			new ProductResponse(2L, "2", 3, "4"),
			new ProductResponse(3L, "2", 3, "4")
		));

		// when
		IntStream.range(0, 10)
			.forEach(i -> productSearchService.getProducts(docs.createRequest()));
		//then
		verify(productQueryRepository, atMost(1)).getProducts(any());

	}


}