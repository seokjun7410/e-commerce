package com.practice.ecommerce.user.infra;

import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.application.service.UserUsecase;
import com.practice.ecommerce.user.infra.web.dto.StoreOwnerRegisterRequest;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserUsecase userUsecase;
	@PostMapping("/admin/sign-up")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> signUp(
		@RequestBody @Valid StoreOwnerRegisterRequest request) {
		userUsecase.register(request, UserType.ADMIN);
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("status","2000"));
	}



}
