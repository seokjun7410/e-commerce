package com.practice.ecommerce.product.infra.web;

import com.practice.ecommerce.ApiTest;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.product.infra.web.step.CategoryStep;
import com.practice.ecommerce.product.infra.web.step.ProductStep;
import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.step.UserStep;
import io.restassured.filter.session.SessionFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("상품 - API 테스트")
public class ProductControllerApiTest extends ApiTest {

	@Test
	public void 상품_등록_API() {
		SessionFilter storeOwnerSession = new SessionFilter();
		SessionFilter adminSession = new SessionFilter();
		UserStep.stoerOwner_signUp_API(spec);
		UserStep.login_API(spec,storeOwnerSession, UserType.STORE_OWNER);
		VirtualStoreOwner.setLoginId("admin");
		UserStep.admin_signUp_API(spec);
		UserStep.login_API(spec,adminSession,UserType.ADMIN);

		//given
		CategoryStep.register_API(spec,adminSession);

		//when
		ProductStep.product_register_API(spec,storeOwnerSession);
	}

}