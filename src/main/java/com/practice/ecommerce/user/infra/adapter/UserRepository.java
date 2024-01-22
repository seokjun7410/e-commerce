package com.practice.ecommerce.user.infra.adapter;

import com.practice.ecommerce.user.domain.User;
import com.practice.ecommerce.user.domain.vo.LoginId;
import com.practice.ecommerce.user.domain.vo.Password;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            UPDATE User u 
            set u.isWithDraw = true
            WHERE u.loginId = :loginId
            """)
    @Modifying
    void logicalDeleteByLoginId(@Param(value = "loginId") LoginId loginId);

    Optional<User> findByLoginIdAndPassword(LoginId loginId, Password password);

    Optional<User> findByLoginId(LoginId loginId);
}
