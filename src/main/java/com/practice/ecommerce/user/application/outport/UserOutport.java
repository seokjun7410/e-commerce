package com.practice.ecommerce.user.application.outport;

import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.domain.User;
import com.practice.ecommerce.user.domain.vo.Address;
import com.practice.ecommerce.user.domain.vo.LoginId;
import com.practice.ecommerce.user.domain.vo.Password;
import java.util.Optional;

public interface UserOutport {

	Optional<User> findUser(LoginId id);

	void register(LoginId idName, Password password, Address address, String name,
		UserType userType);

	void deleteUser(LoginId loginId);

	Optional<User> findByIdPassword(LoginId loginId, Password password);

	boolean isLoginIdIdDuplicated(LoginId loginId);

	void save(User user);
}
