package com.rbs.coding.examples.primes.service;

import com.rbs.coding.examples.primes.model.Primes;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PrimesServiceImplTest {

    private PrimesService testee;

    @Before
    public void setUp() {
        testee = new PrimesServiceImpl();
    }

    @Test
    public void calculatePrimes() {
        assertThat(testee.getPrimes(10L))
                .isEqualToComparingFieldByField(buildPrime(10L, Arrays.asList(2L,3L,5L,7L)));
    }

    @Test
    public void calculatePrimesThrowsExceptionWhenLimitZero() {
        assertThat(testee.getPrimes(0L)).isEqualToComparingFieldByField(buildPrime(0L, Collections.emptyList()));
    }

    @Test
    public void calculatePrimesThrowsExceptionWhenLimitOne() {
        assertThat(testee.getPrimes(1L)).isEqualToComparingFieldByField(buildPrime(1L, Collections.emptyList()));
    }

    private Primes buildPrime(long limit, List<Long> primes) {
        return Primes.builder()
                .initial(limit)
                .primes(primes)
                .build();
    }
}