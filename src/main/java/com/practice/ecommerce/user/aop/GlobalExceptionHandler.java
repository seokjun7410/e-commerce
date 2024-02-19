package com.practice.ecommerce.user.aop;

import com.practice.ecommerce.common.excpetion.CustomException;
import com.practice.ecommerce.common.excpetion.ErrorCode;
import com.practice.ecommerce.common.response.DataResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = CustomException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DataResponse<Object> handleCustomException(CustomException e) {
		log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
		return DataResponse.errorResponse(e.getErrorCode());
	}

	@ExceptionHandler(HttpStatusCodeException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public DataResponse<Object> NullPointerException(HttpStatusCodeException ex) {
		log.warn("message {}",ex.getMessage());
		return DataResponse.errorResponse(ErrorCode.DENIED_UNAUTHORIZED_USER);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object processValidationError(MethodArgumentNotValidException ex,
		HttpServletRequest request) {
		String requestBody = getRequestPayload(ex);
		String requestUrl = request.getRequestURI();

		log.warn("path: {}, 잘못된 BODY : {}", requestUrl, requestBody);

		return DataResponse.errorResponse(ErrorCode.BAD_REQUEST_BODY);
	}

	// HttpServletRequest에서 Request Body를 추출하는 메서드
	private String getRequestPayload(MethodArgumentNotValidException ex) {
		Object requestBody = ex.getBindingResult().getTarget();
		return (requestBody != null) ? requestBody.toString() : "null";
	}
}
