package com.practice.ecommerce.user.infra.web;

import com.practice.ecommerce.excpetion.InvalidLoginInfo;
import com.practice.ecommerce.user.aop.CustomErrorResponse;
import com.practice.ecommerce.user.aop.LoginCheck;
import com.practice.ecommerce.user.application.service.UserUsecase;
import com.practice.ecommerce.user.infra.web.dto.StoreOwnerRegisterRequest;
import com.practice.ecommerce.user.infra.web.dto.UserDeleteRequest;
import com.practice.ecommerce.user.infra.web.dto.UserDto;
import com.practice.ecommerce.user.infra.web.dto.UserLoginRequest;
import com.practice.ecommerce.user.infra.web.dto.UserPasswordUpdateRequest;
import com.practice.ecommerce.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store-owner")
@Slf4j
public class StoreOwnerController {

	private final UserUsecase userUsecase;

	@PostMapping("/sign-up")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> signUp(
		@RequestBody @Valid StoreOwnerRegisterRequest request) {


		userUsecase.storeOwnerRegister(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("status","2000"));
	}

	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(
		@RequestBody @Valid UserLoginRequest request,
		HttpSession session) {


		try {
			userUsecase.login(request.loginId(), request.password());
			SessionUtil.setLoginStoreOwnerId(session, request.loginId());
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (InvalidLoginInfo e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomErrorResponse("아이디와 비번이 일치하지 않습니다."));
		}
	}

	@LoginCheck(type = LoginCheck.UserType.STORE_OWNER)
	@GetMapping("/my-info")
	public ResponseEntity<?> memberInfo(@RequestParam(name = "accountId",required = false) String accountId) {
		String loginId = accountId;
		if (loginId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		UserDto userInfo = userUsecase.getUserInfo(loginId);
		return ResponseEntity.ok(userInfo);
	}

	@GetMapping("/logout")
	@ResponseStatus(HttpStatus.OK)
	public void logout(HttpSession session) {
		SessionUtil.clear(session);
	}

	@PatchMapping("/password")
	@LoginCheck(type = LoginCheck.UserType.STORE_OWNER)
	public ResponseEntity<?> updatePassword(
		@RequestParam(name = "accountId",required = false) String accountId,
		@RequestBody @Valid UserPasswordUpdateRequest request) {



		String loginId = accountId;
		if (loginId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		try {
			userUsecase.updatePassword(loginId, request.password(), request.newPassword());
			return ResponseEntity.ok().build();
		} catch (InvalidLoginInfo e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@LoginCheck(type = LoginCheck.UserType.STORE_OWNER)
	@DeleteMapping
	public ResponseEntity<?> updatePassword(
		@RequestParam(name = "accountId",required = false) String accountId,
		@RequestBody @Valid UserDeleteRequest request,
		BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST,"바인딩 실패, 잘못된 리퀘스트") {};
		}

		try {
			userUsecase.delete(accountId, request.password());
			return ResponseEntity.ok().build();
		} catch (InvalidLoginInfo e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
