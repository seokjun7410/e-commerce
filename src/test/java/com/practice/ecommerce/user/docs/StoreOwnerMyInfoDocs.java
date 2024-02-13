package com.practice.ecommerce.user.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.user.infra.web.dto.UserDto;

public class StoreOwnerMyInfoDocs extends Docs {


	@Override
	public String getIdentifier() {
		return Identifier.STORE_OWNER_MY_INFO_200_OK;
	}

	@Override
	public String getDescription() {
		return "스토어 오너가 자신의 계정정보를 조회할 수 있습니다.";
	}

	@Override
	public String getSummary() {
		return "스토어 오너 계정정보 조회";
	}

	@Override
	public Class<?> getRequestClass() {
		return null;
	}

	@Override
	public Class<?> getResponseClass() {
		return UserDto.class;
	}

	@Override
	public Class<?> getListResponseClass() {
		return null;
	}


}
