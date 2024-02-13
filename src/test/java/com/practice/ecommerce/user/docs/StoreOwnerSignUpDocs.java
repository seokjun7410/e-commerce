package com.practice.ecommerce.user.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.user.infra.web.dto.StoreOwnerRegisterRequest;

public class StoreOwnerSignUpDocs extends Docs {

	public static class Request {
		public static StoreOwnerRegisterRequest create() {
			String loginId = VirtualStoreOwner.getLoginId();
			String password = VirtualStoreOwner.getPassword();
			String nickname = VirtualStoreOwner.getNickName();
			String address = VirtualStoreOwner.getAddress();
			return new StoreOwnerRegisterRequest(loginId, password, nickname, address);
		}
	}
	@Override
	public String getIdentifier() {
		return Identifier.STORE_OWNER_SIGN_UP_201_OK;
	}

	@Override
	public String getDescription() {
		return "스토어 오너 계정을 생성할 수 있습니다.";
	}

	@Override
	public String getSummary() {
		return "스토어 오너 생성";
	}

	@Override
	public Class<?> getRequestClass() {
		return StoreOwnerRegisterRequest.class;
	}

	@Override
	public Class<?> getResponseClass() {
		return null;
	}

	@Override
	public Class<?> getListResponseClass() {
		return null;
	}


}