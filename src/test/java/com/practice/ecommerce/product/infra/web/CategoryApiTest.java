package com.practice.ecommerce.product.infra.web;

import com.practice.ecommerce.ApiTest;
import com.practice.ecommerce.product.infra.web.step.CategoryStep;
import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.step.UserStep;
import io.restassured.filter.session.SessionFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("카테고리 API 테스트")
class CategoryApiTest extends ApiTest {


	@Test
	public void 카테고리를_등록_할_수_있다(){
		SessionFilter sessionFilter = new SessionFilter();
		UserStep.admin_signUp_API(spec);
		UserStep.login_API(spec,sessionFilter, UserType.ADMIN);
		CategoryStep.register_API(spec,sessionFilter);
	}

	@Test
	public void 카테고리를_수정_할_수_있다(){
		SessionFilter sessionFilter = new SessionFilter();
		UserStep.admin_signUp_API(spec);
		UserStep.login_API(spec,sessionFilter, UserType.ADMIN);
		CategoryStep.register_API(spec,sessionFilter);
		CategoryStep.update_API(spec,sessionFilter);
	}

	@Test
	public void 카테고리를_삭제_할_수_있다(){
		SessionFilter sessionFilter = new SessionFilter();
		UserStep.admin_signUp_API(spec);
		UserStep.login_API(spec,sessionFilter, UserType.ADMIN);
		CategoryStep.register_API(spec,sessionFilter);
		CategoryStep.delete_API(spec,sessionFilter);
	}
}