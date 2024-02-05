package com.practice.ecommerce.product.infra.web;

import com.practice.ecommerce.product.application.service.ProductUsecase;
import com.practice.ecommerce.product.infra.web.dto.ProductDetailResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductRegisterRequest;
import com.practice.ecommerce.user.aop.LoginCheck;
import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

	private final ProductUsecase productUsecase;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@LoginCheck(type = UserType.STORE_OWNER)
	public void register(
		@RequestParam(name = "accountId",required = false) String accountId,
		@RequestBody @Valid ProductRegisterRequest registerRequest
	) {
		productUsecase.register(registerRequest,accountId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductDetail(
		@RequestParam(name = "accountId",required = false) String accountId,
		@PathVariable(value = "id") Long id
	) {
		ProductDetailResponse response = productUsecase.getProductDetail(id);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
