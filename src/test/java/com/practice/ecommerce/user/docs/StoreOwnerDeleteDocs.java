package com.practice.ecommerce.user.docs;

import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.user.infra.web.dto.UserDeleteRequest;

public class StoreOwnerDeleteDocs extends Docs {


	@Override
	public String getIdentifier() {
		return Identifier.STORE_OWNER_DELETE_200_OK;
	}

	@Override
	public String getDescription() {
		return "스토어 오너를 삭제할 수 있습니다.";
	}

	@Override
	public String getSummary() {
		return "스토어 오너 삭제";
	}

	@Override
	public Class<?> getRequestClass() {
		return UserDeleteRequest.class;
	}

	@Override
	public Class<?> getResponseClass() {
		return null;
	}

	public UserDeleteRequest createRequest() {
		return new UserDeleteRequest(VirtualStoreOwner.getPassword());
	}
}
