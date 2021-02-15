package com.rbs.coding.examples.primes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApiClientException extends RuntimeException {

    public ApiClientException() {
        super();
    }

    public ApiClientException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApiClientException(final String message) {
        super(message);
    }

    public ApiClientException(final Throwable cause) {
        super(cause);
    }

}
