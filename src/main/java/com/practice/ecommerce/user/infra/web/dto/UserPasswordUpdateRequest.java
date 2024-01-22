package com.practice.ecommerce.user.infra.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserPasswordUpdateRequest(
	@NotNull @NotEmpty String password,
	@NotNull @NotEmpty String newPassword
) {
}
