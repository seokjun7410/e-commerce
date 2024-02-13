package com.practice.ecommerce.product.infra.web;

import com.practice.ecommerce.product.application.service.ProductUsecase;
import com.practice.ecommerce.product.infra.web.dto.ReviewDeleteRequest;
import com.practice.ecommerce.product.infra.web.dto.ReviewRequest;
import com.practice.ecommerce.product.infra.web.dto.ReviewUpdateRequest;
import com.practice.ecommerce.user.aop.LoginCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

	private final ProductUsecase productUsecase;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@LoginCheck(type = LoginCheck.UserType.STORE_OWNER)
	public void register(
		@RequestParam(name = "loginId",required = false) String accountId,
		@RequestBody ReviewRequest request
	) {
		productUsecase.registerReview(request, accountId);
	}

	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	@LoginCheck(type = LoginCheck.UserType.STORE_OWNER)
	public void update(
		@RequestParam(name = "loginId",required = false) String accountId,
		@RequestBody ReviewUpdateRequest request
	) {
		productUsecase.updateReview(accountId,request);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	@LoginCheck(type = LoginCheck.UserType.STORE_OWNER)
	public void delete(
		@RequestParam(name = "loginId",required = false) String loginId,
		@RequestBody ReviewDeleteRequest request
	) {
		productUsecase.deleteReview(loginId,request);
	}
}
