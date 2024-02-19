package com.practice.ecommerce.product.infra.web;

import com.practice.ecommerce.common.response.DataResponse;
import com.practice.ecommerce.common.response.Status;
import com.practice.ecommerce.product.application.service.ProductSearchUsecase;
import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductSearchRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/search")
@Slf4j
public class ProductSearchController {

	private final ProductSearchUsecase productSearchUsecase;
	@PostMapping
	public ResponseEntity<?> searchProduct(
		@RequestParam(value = "accountId", required = false) String accountId,
		@RequestBody ProductSearchRequest request
	) {
		List<ProductResponse> products = productSearchUsecase.getProducts(request);
		return ResponseEntity.status(HttpStatus.OK)
			.body(DataResponse.response(Status.SUCCESS,products));
	}

}
