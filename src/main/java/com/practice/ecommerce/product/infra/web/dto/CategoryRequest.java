package com.practice.ecommerce.product.infra.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Description;

public record CategoryRequest(
	@NotNull @NotEmpty String name
) { }
