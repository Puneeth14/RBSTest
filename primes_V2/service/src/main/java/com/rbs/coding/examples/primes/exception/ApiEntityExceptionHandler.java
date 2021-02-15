package com.rbs.coding.examples.primes.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ApiEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public ApiEntityExceptionHandler() {
        super();
    }

    @ExceptionHandler(value = { ConstraintViolationException.class, ApiClientException.class })
    public final ResponseEntity<Object> handleClientException(final RuntimeException e, final WebRequest request) {
        logger.error("Bad Request: ", e);
        return handleExceptionInternal(e, buildError(HttpStatus.BAD_REQUEST, "API_CLIENT_ERROR", e),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ApiError buildError(HttpStatus status, String code, Exception e) {
        return ApiError.builder()
                .id(String.valueOf(status.value()))
                .code(code)
                .message(e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage())
                .build();
    }
}
