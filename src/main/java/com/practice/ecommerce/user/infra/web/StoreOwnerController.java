package com.practice.ecommerce.user.infra.web;

import static org.springframework.http.HttpStatus.OK;

import com.practice.ecommerce.common.response.DataResponse;
import com.practice.ecommerce.common.response.Status;
import com.practice.ecommerce.user.aop.LoginCheck;
import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.application.service.UserUsecase;
import com.practice.ecommerce.user.infra.web.dto.StoreOwnerRegisterRequest;
import com.practice.ecommerce.user.infra.web.dto.UserDeleteRequest;
import com.practice.ecommerce.user.infra.web.dto.UserLoginRequest;
import com.practice.ecommerce.user.infra.web.dto.UserPasswordUpdateRequest;
import com.practice.ecommerce.user.infra.web.dto.UserResponse;
import com.practice.ecommerce.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store-owner")
@Slf4j
public class StoreOwnerController {

	private final UserUsecase userUsecase;

	@PostMapping("/sign-up")
	@ResponseStatus(HttpStatus.CREATED)
	public void signUp(
		@RequestBody @Valid StoreOwnerRegisterRequest request) {
		userUsecase.register(request, UserType.STORE_OWNER);
	}

	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(
		@RequestParam(name = "userType") UserType userType,
		@RequestBody @Valid UserLoginRequest request,
		HttpSession session) {

		UserResponse userResponse = userUsecase.login(request.loginId(), request.password());
		switch (userType) {
			case ADMIN -> SessionUtil.setLoginAdminId(session, request.loginId());
			case STORE_OWNER -> SessionUtil.setLoginStoreOwnerId(session, request.loginId());
			case USER -> SessionUtil.setLoginUserId(session, request.loginId());
		}

		return ResponseEntity.status(OK)
			.body(DataResponse.response(Status.SUCCESS, userResponse));
	}

	@LoginCheck(type = LoginCheck.UserType.STORE_OWNER)
	@GetMapping("/my-info")
	public ResponseEntity<?> memberInfo(
		@RequestParam(name = "loginId", required = false) String loginId) {
		UserResponse userResponse = userUsecase.getUserInfo(loginId);

		return ResponseEntity.status(OK)
			.body(DataResponse.response(Status.SUCCESS, userResponse));
	}

	@GetMapping("/logout")
	@ResponseStatus(OK)
	public void logout(HttpSession session) {
		SessionUtil.clear(session);
	}

	@PatchMapping("/password")
	@LoginCheck(type = LoginCheck.UserType.STORE_OWNER)
	public void updatePassword(
		@RequestParam(name = "loginId", required = false) String loginId,
		@RequestBody @Valid UserPasswordUpdateRequest request) {

		userUsecase.updatePassword(loginId, request.password(), request.newPassword());
	}

	@LoginCheck(type = LoginCheck.UserType.STORE_OWNER)
	@DeleteMapping
	public void updatePassword(
		@RequestParam(name = "loginId", required = false) String loginId,
		@RequestBody @Valid UserDeleteRequest request) {

		userUsecase.delete(loginId, request.password());
	}
}
