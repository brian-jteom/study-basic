package com.junt.studybasic.exception;

import com.junt.studybasic.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler({BlogNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> notFoundException() {
        ResponseCode responseCode = ResponseCode.NOT_FOUND;
        return ResponseEntity.status(responseCode.getHttpStatus()).body(new ErrorResponse(responseCode));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> runtimeException(Exception e) {
        log.error("runtimeException INTERNAL_SERVER_ERROR", e);

        ResponseCode responseCode = ResponseCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(responseCode));
    }



}
