package com.rbs.coding.examples.primes.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.springframework.stereotype.Service;
import com.rbs.coding.examples.primes.model.Primes;

@Service
public class PrimesServiceImpl implements PrimesService {

    private static final long FIRST_PRIME_TWO = 2;

    @Override
    public Primes getPrimes(long limit) {
        List<Long> primes = LongStream
                .rangeClosed(FIRST_PRIME_TWO, limit)
                .filter(this::isPrime)
                .boxed()
                .collect(Collectors.toList());

        return Primes.builder().primes(primes).initial(limit).build();
    }

    public boolean isPrime(long streamValue) {
        if (streamValue <= 1) {
            return false;
        }
        for (long i = FIRST_PRIME_TWO; i <= streamValue / FIRST_PRIME_TWO; i++) {
            if (streamValue % i == 0) {
                return false;
            }
        }
        return true;
    }
}


