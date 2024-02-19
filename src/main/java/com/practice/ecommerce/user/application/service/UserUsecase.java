package com.practice.ecommerce.user.application.service;

import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.infra.web.dto.StoreOwnerRegisterRequest;
import com.practice.ecommerce.user.infra.web.dto.UserResponse;

public interface UserUsecase {

	void register(StoreOwnerRegisterRequest request, UserType userType);

	UserResponse login(String loginId, String password);

	boolean isDuplicatedId(String loginId);

	void updatePassword(String loginId, String password, String newPassword);

	void delete(String loginId, String password);

	UserResponse getUserInfo(String loginId);
}
