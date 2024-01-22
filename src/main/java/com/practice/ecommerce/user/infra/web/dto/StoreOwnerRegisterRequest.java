package com.practice.ecommerce.user.infra.web.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StoreOwnerRegisterRequest(
	@NotNull @NotEmpty String loginId,
	@NotNull @NotEmpty String password,
	@NotNull @NotEmpty String nickName,
	@NotNull @NotEmpty String address
) { }
