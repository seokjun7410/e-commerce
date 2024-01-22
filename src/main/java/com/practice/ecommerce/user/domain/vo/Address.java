package com.practice.ecommerce.user.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Address {
	private String si; //~시
	private String gu; //~구
	private String doro; //도로명 주소
	private String jibun; //지번 주소

	public static Address create(String address){
	        return new Address(null,null,address,null);
	    }
}
