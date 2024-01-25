package com.practice.ecommerce.user.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.user.infra.web.dto.UserPasswordUpdateRequest;

public class StoreOwnerUpdatePasswordDocs extends Docs {



	@Override
	public String getIdentifier() {
		return Identifier.STORE_OWNER_UPDATE_PASSWORD_200_OK;
	}

	@Override
	public String getDescription() {
		return "스토어 오너 비밀번호를 변경할 수 있습니다.";
	}

	@Override
	public String getSummary() {
		return "스토어 오너 비밀번호 변경";
	}

	@Override
	public Class<?> getRequestClass() {
		return UserPasswordUpdateRequest.class;
	}

	@Override
	public Class<?> getResponseClass() {
		return null;
	}


	public static UserPasswordUpdateRequest requestCreate(String newPassword) {
		String password = VirtualStoreOwner.getPassword();
		return new UserPasswordUpdateRequest(password,newPassword);
	}
}
