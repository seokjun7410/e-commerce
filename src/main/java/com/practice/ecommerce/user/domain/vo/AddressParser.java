package com.practice.ecommerce.user.domain.vo;

public class AddressParser {
	public static Sigu parse(String addressValue) throws IllegalArgumentException{
		String[] split = addressValue.split(" ");
		if(split.length < 2)
			throw new IllegalArgumentException();

		String si = split[0];
		String gu = split[1];

		return new Sigu(si, gu);
	}

	public record Sigu(String si, String gu) { }
}
