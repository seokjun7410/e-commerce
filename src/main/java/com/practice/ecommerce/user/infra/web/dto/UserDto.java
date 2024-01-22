package com.practice.ecommerce.user.infra.web.dto;

import com.practice.ecommerce.user.domain.User;
import com.practice.ecommerce.user.domain.vo.Address;
import java.time.LocalDateTime;

public record UserDto(
	Long id,
	String userId,
	String nickName,
	Address address,
	boolean isWithDraw,
	LocalDateTime createdTime,
	LocalDateTime updatedTime
) {

	public static UserDto from(User user) {
		return new UserDto(user.getId(),
			user.getLoginId().getUserId(),
			user.getName(),
			user.getAddress(),
			user.isWithDraw(),
			user.getCreatedDate(),
			user.getModifiedDate());
	}

	public enum Status {
		DEFAULT, ADMIN, DELETED
	}
}
