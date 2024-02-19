package com.practice.ecommerce.user.application.service.impl;

import com.practice.ecommerce.common.excpetion.CustomException;
import com.practice.ecommerce.common.excpetion.ErrorCode;
import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.application.outport.UserOutport;
import com.practice.ecommerce.user.application.service.UserUsecase;
import com.practice.ecommerce.user.domain.User;
import com.practice.ecommerce.user.domain.vo.Address;
import com.practice.ecommerce.user.domain.vo.LoginId;
import com.practice.ecommerce.user.domain.vo.Password;
import com.practice.ecommerce.user.infra.web.dto.StoreOwnerRegisterRequest;
import com.practice.ecommerce.user.infra.web.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService implements UserUsecase {

	private final UserOutport userOutport;

	@Transactional
	@Override
	public void register(StoreOwnerRegisterRequest request, UserType userType) {
		if (isDuplicatedId(request.loginId()))
			throw new CustomException(ErrorCode.DUPLICATE_ID_EXCEPTION);

		LoginId loginId = LoginId.create(request.loginId());
		Password password = Password.create(request.password());
		Address address = Address.create(request.address(), request.address());

		userOutport.register(loginId, password, address, request.nickName(), userType);
	}

	@Override
	public UserResponse login(String loginId, String password){
		User user = getUserElseThrow(loginId, password);
		return UserResponse.from(user);
	}

	@Override
	public boolean isDuplicatedId(String loginId) {
		return userOutport.isLoginIdIdDuplicated(LoginId.create(loginId));
	}

	@Transactional
	@Override
	public void updatePassword(String loginId, String password, String newPassword){
		User user = getUserElseThrow(loginId, password);
		user.updatePassword(Password.create(newPassword));
		userOutport.save(user);
	}

	@Transactional
	@Override
	public void delete(String loginId, String password){
		User user = getUserElseThrow(loginId, password);
		userOutport.deleteUser(user.getLoginId());
	}

	@Override
	public UserResponse getUserInfo(String loginId) {
		User user = userOutport.findUser(LoginId.create(loginId))
			.orElseThrow(()->new CustomException(ErrorCode.USER_IS_NOT_FOUND.appendId(loginId)));
		return UserResponse.from(user);
	}

	private User getUserElseThrow(String loginId, String password){
		LoginId loginIdVo = LoginId.create(loginId);
		Password passwordVo = Password.create(password);

		return userOutport.findByIdPassword(loginIdVo, passwordVo)
			.orElseThrow(() -> new CustomException(ErrorCode.INVALID_LOGIN_INFO));
	}
}
