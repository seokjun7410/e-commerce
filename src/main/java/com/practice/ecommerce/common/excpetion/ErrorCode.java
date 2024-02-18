package com.practice.ecommerce.common.excpetion;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* ------------------ 400 BAD_REQUEST : 잘못된 요청 ------------------ */

    INVALID_LOGIN_INFO(BAD_REQUEST, "아이디 또는 비밀번호가 일치하지 않습니다."),
    DUPLICATE_ID_EXCEPTION(BAD_REQUEST, "중복되는 ID입니다. 다른 ID를 입력해주세요."),



    /* ------------------ 401 BAD_REQUEST : 권한 없음 ------------------ */
    DENIED_UNAUTHORIZED_USER(UNAUTHORIZED, "로그인되지 않은 유저의 접근입니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
