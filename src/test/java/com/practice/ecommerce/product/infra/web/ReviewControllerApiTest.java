package com.practice.ecommerce.product.infra.web;

import com.practice.ecommerce.ApiTest;
import com.practice.ecommerce.docsUtils.VirtualReview;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.product.infra.web.dto.ReviewType;
import com.practice.ecommerce.product.infra.web.step.CategoryStep;
import com.practice.ecommerce.product.infra.web.step.ProductStep;
import com.practice.ecommerce.product.infra.web.step.ReviewStep;
import com.practice.ecommerce.user.aop.LoginCheck.UserType;
import com.practice.ecommerce.user.step.UserStep;
import io.restassured.filter.session.SessionFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("리뷰 API 테스트")
public class ReviewControllerApiTest extends ApiTest {

	@Test
	public void 상품_리뷰를_등록할_수_있다() {
		// given
		SessionFilter adminSession = adminLogin();
		CategoryStep.register_API(spec,adminSession);

		SessionFilter storeOwnerSession = storeOwnerLogin();
		ProductStep.product_register_API(spec,storeOwnerSession);

		// when // then
		ReviewStep.register_API(spec,storeOwnerSession);
	}

	@Test
	public void 서브_리뷰를_등록할_수_있다() {
		// given
		SessionFilter adminSession = adminLogin();
		CategoryStep.register_API(spec,adminSession);

		SessionFilter storeOwnerSession = storeOwnerLogin();
		ProductStep.product_register_API(spec,storeOwnerSession);

		// when // then
		ReviewStep.register_API(spec,storeOwnerSession);
		VirtualReview.setReviewType(ReviewType.SUB_REVIEW);
		ReviewStep.register_API(spec,storeOwnerSession);
	}

	@Test
	public void 리뷰를_업데이트할_수_있다() {
		// given
		SessionFilter adminSession = adminLogin();
		CategoryStep.register_API(spec,adminSession);

		SessionFilter storeOwnerSession = storeOwnerLogin();
		ProductStep.product_register_API(spec,storeOwnerSession);

		// when // then
		ReviewStep.register_API(spec,storeOwnerSession);
		VirtualReview.setContent("리뷰내용 수정");
		ReviewStep.update_API(spec,storeOwnerSession);
	}
	@Test
	public void 리뷰를_삭제할_수_있다() {
		// given
		SessionFilter adminSession = adminLogin();
		CategoryStep.register_API(spec,adminSession);

		SessionFilter storeOwnerSession = storeOwnerLogin();
		ProductStep.product_register_API(spec,storeOwnerSession);

		// when // then
		ReviewStep.register_API(spec,storeOwnerSession);
		ReviewStep.delete_API(spec,storeOwnerSession);
	}

	private SessionFilter storeOwnerLogin() {
		UserStep.stoerOwner_signUp_API(spec);
		SessionFilter storeOwnerSession = new SessionFilter();
		UserStep.login_API(spec,storeOwnerSession, UserType.STORE_OWNER);
		return storeOwnerSession;
	}

	private SessionFilter adminLogin() {
		SessionFilter adminSession = new SessionFilter();
		VirtualStoreOwner.setLoginId("admin");
		UserStep.admin_signUp_API(spec);
		UserStep.login_API(spec,adminSession,UserType.ADMIN);
		VirtualStoreOwner.clear();
		return adminSession;
	}
}