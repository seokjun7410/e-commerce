package com.practice.ecommerce.user.domain;

import com.practice.ecommerce.BaseEntity;
import com.practice.ecommerce.user.domain.vo.Address;
import com.practice.ecommerce.user.domain.vo.LoginId;
import com.practice.ecommerce.user.domain.vo.Password;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER")
public class User extends BaseEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private LoginId loginId;
	@Embedded
	private Password password;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "password", column = @Column(name = "past_password")),
	})
	private Password pastPassword;
	@Embedded
	private Address address;

	private String name;
	private boolean isWithDraw;

	public User(LoginId loginId, Password password, Password pastPassword, Address address,
		String name, boolean isWithDraw) {
		this.loginId = loginId;
		this.password = password;
		this.pastPassword = pastPassword;
		this.address = address;
		this.name = name;
		this.isWithDraw = isWithDraw;
	}

	public static User createStoreOwner(LoginId loginId, Password password, Address address,
		String name) {
		return new User(loginId, password,password, address, name,false);
	}

	public void updatePassword(String encryptPassword) {
		this.pastPassword = this.password;
		this.password = Password.create(encryptPassword);
	}

}
