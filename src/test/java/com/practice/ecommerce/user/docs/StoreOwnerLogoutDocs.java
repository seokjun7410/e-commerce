package com.practice.ecommerce.user.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;

public class StoreOwnerLogoutDocs extends Docs {



	@Override
	public String getIdentifier() {
		return Identifier.STORE_OWNER_LOGOUT_200_OK;
	}

	@Override
	public String getDescription() {
		return "스토어 오너가 로그아웃 할 수 있습니다.";
	}

	@Override
	public String getSummary() {
		return "스토어 오너 로그아웃";
	}

	@Override
	public Class<?> getRequestClass() {
		return null;
	}

	@Override
	public Class<?> getResponseClass() {
		return null;
	}


}
