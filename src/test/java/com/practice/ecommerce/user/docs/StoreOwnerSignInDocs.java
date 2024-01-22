package com.practice.ecommerce.user.docs;


import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;

public class StoreOwnerSignInDocs extends Docs {


	public StoreOwnerSignInDocs(Class<?> requestClass) {
		super(requestClass);
	}

	@Override
	public String getIdentifier() {
		return Identifier.STORE_OWNER_SIGN_IN_200_OK;
	}

	@Override
	public String getDescription() {
		return "스토어 오너가 로그인할 수 있습니다.";
	}

	@Override
	public String getSummary() {
		return "스토어 오너 로그인";
	}

	@Override
	protected ResponseDescription[] getResponseDescription() {
		return new ResponseDescription[0];
	}


}
