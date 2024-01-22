package com.practice.ecommerce.user.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;

public class StoreOwnerMyInfoDocs extends Docs {

	public StoreOwnerMyInfoDocs(Class<?> requestClass) {
		super(requestClass);
	}

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
	protected ResponseDescription[] getResponseDescription() {
		return new ResponseDescription[]{
			new ResponseDescription("id","PK"),
			new ResponseDescription("userId","유저 로그인 ID"),
			new ResponseDescription("nickName","별명"),
			new ResponseDescription("address.si","~시 (서울시)"),
			new ResponseDescription("address.gu","~구 (강남구)"),
			new ResponseDescription("address.doro","도로명 주소"),
			new ResponseDescription("address.jibun","지번 주소"),
			new ResponseDescription("isWithDraw","삭제 여부"),
			new ResponseDescription("createdTime","생성 시간"),
			new ResponseDescription("updatedTime","변경 시간")
		};
	}
}
