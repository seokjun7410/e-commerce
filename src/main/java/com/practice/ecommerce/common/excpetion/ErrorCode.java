package com.practice.ecommerce.common.excpetion;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Slf4j
public enum ErrorCode {

    /* ------------------ 400 BAD_REQUEST : 잘못된 요청 ------------------ */

    INVALID_LOGIN_INFO(BAD_REQUEST, "아이디 또는 비밀번호가 일치하지 않습니다."),
    DUPLICATE_ID_EXCEPTION(BAD_REQUEST, "중복되는 ID입니다. 다른 ID를 입력해주세요."),
    BAD_REQUEST_BODY(BAD_REQUEST,"잘못된 Body 입니다. 문서를 참고하여 올바른 데이터를 첨부해주세요."),
    STORE_OWNER_IS_NOT_FOUND(BAD_REQUEST, "존재하지 않는 StoreOwner 입니다. "+ ErrorCode.idExpression),
    USER_IS_NOT_FOUND(BAD_REQUEST,"존재하지 않는 User 입니다. "+ErrorCode.idExpression),
    CATEGORY_IS_NOT_FOUND(BAD_REQUEST,"존재하지 않는 Category 입니다."+ErrorCode.idExpression),
    /* ------------------ 401 BAD_REQUEST : 권한 없음 ------------------ */
    DENIED_UNAUTHORIZED_USER(UNAUTHORIZED, "로그인되지 않은 유저의 접근입니다.");

    private final HttpStatus httpStatus;
    private String detail;
    private final static String idExpression = "id:";

    public ErrorCode appendDetail(String text){
        this.detail = this.detail+" "+text;
        return this;
    }

    public ErrorCode appendId(Object id){
        String value = (String) id;
        if(!AvailableAppendId()) {
            log.warn(this.name() + " 에러코드가 ID표현식을 지키고 있지 않습니다.");
        }
        this.detail = this.detail+" "+value;
        return this;
    }

    private boolean AvailableAppendId() {
        return this.detail.endsWith(ErrorCode.idExpression);
    }
}
