package com.practice.ecommerce.user.infra.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserDeleteRequest(
	@NotEmpty @NotNull String password
) {

}
