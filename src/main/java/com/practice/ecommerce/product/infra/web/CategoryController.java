package com.practice.ecommerce.product.infra.web;

import com.practice.ecommerce.product.application.service.CategoryUseCase;
import com.practice.ecommerce.product.infra.web.dto.CategoryRequest;
import com.practice.ecommerce.user.aop.LoginCheck;
import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
@Slf4j
public class CategoryController {

	private final CategoryUseCase categoryUseCase;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@LoginCheck(type = LoginCheck.UserType.ADMIN)
	public void register(
		@RequestParam(name = "loginId", required = false) String loginId,
		@RequestBody @Valid CategoryRequest categoryDTO) {
		categoryUseCase.register(categoryDTO);
	}

	@PatchMapping("/{categoryId}")
	@LoginCheck(type = UserType.ADMIN)
	public void update(
		@RequestParam(name = "loginId", required = false) String loginId,
		@PathVariable(name = "categoryId") Long categoryId,
		@RequestBody @Valid CategoryRequest categoryRequest) {
		categoryUseCase.update(categoryId, categoryRequest);
	}


	@DeleteMapping("/{categoryId}")
	@LoginCheck(type = UserType.ADMIN)
	public void delete(
		@RequestParam(name = "loginId", required = false) String loginId,
		@PathVariable(name = "categoryId") Long categoryId) {
		categoryUseCase.delete(categoryId);
	}
}
