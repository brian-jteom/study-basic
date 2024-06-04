package com.junt.studybasic.dto;

import com.junt.studybasic.exception.ResponseCode;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private int httpStatus;
    private String errorCode;
    private long timestamp;
    private String message;
    private Object detail;


    public ErrorResponse(ResponseCode responseCode) {
        this.httpStatus = responseCode.getHttpStatus().value();
        this.errorCode = responseCode.getCode();
        this.timestamp = System.currentTimeMillis();
        this.message = responseCode.getMessage();
    }
}
