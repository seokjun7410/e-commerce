package com.practice.ecommerce.user.application.service;

import com.practice.ecommerce.excpetion.InvalidLoginInfo;
import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.infra.web.dto.StoreOwnerRegisterRequest;
import com.practice.ecommerce.user.infra.web.dto.UserDto;

public interface UserUsecase {

	int register(StoreOwnerRegisterRequest request, UserType userType);

	UserDto login(String loginId, String password) throws InvalidLoginInfo;

	boolean isDuplicatedId(String loginId);

	void updatePassword(String loginId, String password, String newPassword)
		throws InvalidLoginInfo;

	void delete(String loginId, String password) throws InvalidLoginInfo;

	UserDto getUserInfo(String loginId);
}
