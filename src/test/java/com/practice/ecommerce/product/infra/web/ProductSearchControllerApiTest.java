package com.practice.ecommerce.product.infra.web;

import com.practice.ecommerce.ApiTest;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.product.assertcluster.AssertCluster;
import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import com.practice.ecommerce.product.infra.web.step.CategoryStep;
import com.practice.ecommerce.product.infra.web.step.ProductStep;
import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.step.UserStep;
import io.restassured.filter.session.SessionFilter;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("API 테스트 - 상품 목록 조회")
public class ProductSearchControllerApiTest extends ApiTest {

	@Test
	void 상품_목록_조회_API() {
		SessionFilter storeOwnerSession = new SessionFilter();
		SessionFilter adminSession = new SessionFilter();
		UserStep.stoerOwner_signUp_API(spec);
		UserStep.login_API(spec,storeOwnerSession, UserType.STORE_OWNER);
		VirtualStoreOwner.setLoginId("admin");
		UserStep.admin_signUp_API(spec);
		UserStep.login_API(spec,adminSession,UserType.ADMIN);

		//given
		CategoryStep.register_API(spec,adminSession);
		ProductStep.product_register_API(spec,storeOwnerSession);
		ProductStep.product_register_API(spec,storeOwnerSession);
		ProductStep.product_register_API(spec,storeOwnerSession);

		// then
		List<ProductResponse> productResponses = ProductStep.product_search_API(spec);

		AssertCluster.productResponse(productResponses);

	}


}