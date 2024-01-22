package com.practice.ecommerce.user.application.service.impl;

import com.practice.ecommerce.excpetion.DuplicateIdException;
import com.practice.ecommerce.excpetion.InvalidLoginInfo;
import com.practice.ecommerce.user.application.outport.UserOutport;
import com.practice.ecommerce.user.application.service.UserService;
import com.practice.ecommerce.user.domain.User;
import com.practice.ecommerce.user.domain.vo.Address;
import com.practice.ecommerce.user.domain.vo.LoginId;
import com.practice.ecommerce.user.domain.vo.Password;
import com.practice.ecommerce.user.infra.web.dto.StoreOwnerRegisterRequest;
import com.practice.ecommerce.user.infra.web.dto.UserDto;
import com.practice.ecommerce.util.SHA256Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link Transactional} query, command 트랜잭션 분리 <br> {@link Slf4j} Logback Log4j 퍼샤드 패턴으로 이용 <br>
 * {@link UserService} PSA 및 서비스 명세를 위한 추상화 <br> {@link UserOutport} DAO계층의 자유로운 변경을 위한 DIP 구성 <br>
 * <br>
 *
 * @Code-Description <br>
 * @1. 모니터링 대비 각 메소드에 맞는 로깅 <br>
 * @2. {@link #findByIdAndPasswordElseThrow(String, String, String)} error처리와 자주 사용되는 메소드 private
 * 추출<br>
 * @3. {@link SHA256Util} SHA256기법을 이용한 password 암호화}
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserOutport userOutport;

	@Override
	public int storeOwnerRegister(StoreOwnerRegisterRequest request) {
		boolean duplicateResult = isDuplicatedId(request.loginId());
		if (duplicateResult) {
			throw new DuplicateIdException("중복된 아이디입니다.");
		}

		LoginId loginId = LoginId.create(request.loginId());
		Password password = Password.create(request.password());
		Address address = Address.create(request.address());

		try {
			userOutport.registerStoreOwner(loginId, password, address, request.nickName());
		} catch (Exception e) {
			log.error("STORE OWNER REGISTER ERROR! {}", request,e.getCause());
			throw new RuntimeException("INSERT ERROR!");
		}

		return 1;
	}

	@Override
	public UserDto login(String loginId, String password) throws InvalidLoginInfo {
		User user = findByIdAndPasswordElseThrow(loginId, password, "LOGIN FAIL!");
		return UserDto.from(user);
	}

	@Override
	public boolean isDuplicatedId(String loginId) {
		return userOutport.isLoginIdIdDuplicated(LoginId.create(loginId));
	}

	@Transactional
	@Override
	public void updatePassword(String loginId, String password, String newPassword)
		throws InvalidLoginInfo {

		User user = findByIdAndPasswordElseThrow(loginId, password, "UPDATE USER PASSWORD FAIL");
		user.updatePassword(newPassword);
		try {
			userOutport.save(user);
		} catch (Exception e) {
			log.error("UPDATE PASSWORD ERROR loginId: {}", loginId);
			throw new RuntimeException("UPDATE PASSWORD ERROR!");
		}
	}

	@Override
	public void delete(String loginId, String password) throws InvalidLoginInfo {
		User user = findByIdAndPasswordElseThrow(loginId, password, "USER DELETE FAIL");
		userOutport.deleteUser(user.getLoginId());
	}

	@Override
	public UserDto getUserInfo(String loginId) {
		return UserDto.from(userOutport.getUser(LoginId.create(loginId)).orElseThrow(() -> {
				log.error("GET USER INFO ERROR loginId :{}",loginId);
				return new IllegalArgumentException("GET USER INFO ERROR");
			})
		);
	}


	private User findByIdAndPasswordElseThrow(String loginId, String password,
		String messageIfThrow) throws InvalidLoginInfo {

		LoginId loginIdVo = LoginId.create(loginId);
		Password passwordVo = Password.create(password);

		return userOutport.findByIdPassword(loginIdVo, passwordVo).orElseThrow(() -> {
			log.warn(messageIfThrow + "id: {} {}", loginId);
			return new InvalidLoginInfo(messageIfThrow);
		});
	}
}
