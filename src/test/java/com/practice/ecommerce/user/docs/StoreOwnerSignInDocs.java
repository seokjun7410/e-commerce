package com.practice.ecommerce.user.docs;


import com.practice.ecommerce.docsUtils.Docs;
import com.practice.ecommerce.docsUtils.Identifier;
import com.practice.ecommerce.user.infra.web.dto.UserLoginRequest;

public class StoreOwnerSignInDocs extends Docs {

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
	public Class<?> getRequestClass(){
		return UserLoginRequest.class;
	}
	@Override
	public Class<?> getResponseClass(){
		return null ;
	}


}
