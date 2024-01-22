package com.practice.ecommerce.user.infra.adapter;

import com.practice.ecommerce.user.application.outport.UserOutport;
import com.practice.ecommerce.user.domain.User;
import com.practice.ecommerce.user.domain.vo.Address;
import com.practice.ecommerce.user.domain.vo.LoginId;
import com.practice.ecommerce.user.domain.vo.Password;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserAdapter implements UserOutport {

    private final UserRepository userRepository;
    @Override
    public Optional<User> getUser(LoginId id) {
        return userRepository.findByLoginId(id);
    }

    @Override
    public void registerStoreOwner(LoginId loginId, Password password, Address address,String name) {
        User storeOwner = User.createStoreOwner(loginId,password,address,name);
        userRepository.save(storeOwner);
    }

    @Override
    public void deleteUser(LoginId loginId) {
         userRepository.logicalDeleteByLoginId(loginId);
    }

    @Override
    public Optional<User> findByIdPassword(LoginId loginId, Password password) {
        return userRepository.findByLoginIdAndPassword(loginId,password);
    }

    @Override
    public boolean isLoginIdIdDuplicated(LoginId loginId) {
        return userRepository.findByLoginId(loginId).orElse(null) != null;
    }


    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
