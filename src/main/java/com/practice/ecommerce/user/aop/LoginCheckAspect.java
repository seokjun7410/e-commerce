package com.practice.ecommerce.user.aop;

import com.practice.ecommerce.common.excpetion.CustomException;
import com.practice.ecommerce.common.excpetion.ErrorCode;
import com.practice.ecommerce.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Slf4j
@Component
public class LoginCheckAspect {

	@Around("@annotation(com.practice.ecommerce.user.aop.LoginCheck) && @annotation(loginCheck)")
	public Object adminLoginCheck(ProceedingJoinPoint proceedingJoinPoint, LoginCheck loginCheck)
		throws Throwable {
		HttpSession session = (HttpSession) ((ServletRequestAttributes) (RequestContextHolder
			.currentRequestAttributes())).getRequest().getSession();

		String id = null;
		switch (loginCheck.type()) {
			case USER -> id = SessionUtil.getLoginUserId(session);
			case STORE_OWNER -> id = SessionUtil.getLoginStoreOwnerId(session);
			case ADMIN -> id = SessionUtil.getLoginAdminId(session);
		}

		if (id == null) {
			log.error(proceedingJoinPoint.toString() + "로그인 되지 않은 사용자 접근");
			throw new CustomException(ErrorCode.DENIED_UNAUTHORIZED_USER) {
			};
		}

		Object[] methodArgs = proceedingJoinPoint.getArgs();
		if (proceedingJoinPoint != null) {
			methodArgs[0] = id;
		}

		return proceedingJoinPoint.proceed(methodArgs);
	}

}
