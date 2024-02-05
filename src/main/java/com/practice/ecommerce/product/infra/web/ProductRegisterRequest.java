package com.practice.ecommerce.product.infra.web;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductRegisterRequest(
	@NotNull @NotEmpty String name,
	@NotNull @NotEmpty String explanation,
	@NotNull Integer price,
	@NotNull Integer stock,
	@NotNull Long categoryId) {

}
