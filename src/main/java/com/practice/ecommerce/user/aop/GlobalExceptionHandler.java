package com.practice.ecommerce.user.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(HttpStatusCodeException.class)
	public ResponseEntity<CustomErrorResponse> NullPointerException(HttpStatusCodeException ex) {
		CustomErrorResponse errorResponse = new CustomErrorResponse(ex.getMessage());
		return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object processValidationError(MethodArgumentNotValidException ex,
		HttpServletRequest request) {
		String requestBody = getRequestPayload(ex);
		String requestUrl = request.getRequestURI();

		log.warn("path: {}, 잘못된 BODY : {}", requestUrl, requestBody);

		CustomErrorResponse customErrorResponse = new CustomErrorResponse(
			"잘못된 Body 입니다. 문서를 참고하여 올바른 데이터를 첨부해주세요.");
		return ResponseEntity.status(ex.getStatusCode()).body(customErrorResponse);
	}

	// HttpServletRequest에서 Request Body를 추출하는 메서드
	private String getRequestPayload(MethodArgumentNotValidException ex) {
		Object requestBody = ex.getBindingResult().getTarget();
		return (requestBody != null) ? requestBody.toString() : "null";
	}
}
