package com.junt.studybasic.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
    /**
     * 4xx Client Error
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청 입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "데이터를 찾을 수 없습니다."),
    CONFLICT(HttpStatus.CONFLICT, "중복된 리소스 형식입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "접속 권한 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다."),

    /**
     * 5xx Server Error
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류"),

    /**
     * 외부 API 오류
     */
    SMARTSTORE_SERVER_ERROR(HttpStatus.BAD_REQUEST, "스마트스토어 서버 오류"),
    CAFE24_SERVER_ERROR(HttpStatus.BAD_REQUEST, "cafe24 서버 오류"),




    /**
     * Custom Response
     */
    SUCCESS(HttpStatus.OK, "Success"),
    FAIL(HttpStatus.OK, "Fail"),
    /**
     * 사용자 정의 Error
     */
    CATEGORY_NOT_MATCH(HttpStatus.BAD_REQUEST, "category 매핑이 되지 않았습니다."),
    PARSE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파싱 오류가 생겼습니다.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ResponseCode(HttpStatus httpStatus, String defaultMessage) {
        this.httpStatus = httpStatus;
        this.code = String.valueOf(httpStatus.value());
        this.message = defaultMessage;
    }

    ResponseCode(HttpStatus httpStatus, String code, String defaultMessage) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = defaultMessage;
    }
}
