package com.rbs.coding.examples.primes.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Primes {
    private Long initial;
    private List<Long> primes;
}
