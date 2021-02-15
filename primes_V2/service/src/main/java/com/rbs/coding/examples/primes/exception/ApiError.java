package com.rbs.coding.examples.primes.exception;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class ApiError {
    private String id;
    private String code;
    private String message;
    private String detail;
}
