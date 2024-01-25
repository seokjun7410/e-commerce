package com.practice.ecommerce.user.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.practice.ecommerce.user.domain.vo.AddressParser.Sigu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AddressParser 테스트")
class AddressParserTest {

	@Test
	void parse() {
		Sigu parse = AddressParser.parse("서울특별시 강남구 역삼동 12-3");

		assertThat(parse).usingRecursiveComparison().isEqualTo(new Sigu("서울특별시","강남구"));
	}

	@Test
	void parse_시_구_파싱불가_IllegalArgumentExcpetion() {
		assertThrows(IllegalArgumentException.class, ()->{
			Sigu parse = AddressParser.parse("서울특별시");
		});
	}
}