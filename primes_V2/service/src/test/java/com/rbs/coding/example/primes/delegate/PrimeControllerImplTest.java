package com.rbs.coding.example.primes.delegate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import com.rbs.coding.examples.primes.api.model.PrimesResponse;
import com.rbs.coding.examples.primes.controller.PrimeControllerImpl;
import com.rbs.coding.examples.primes.model.Primes;
import com.rbs.coding.examples.primes.service.PrimesService;

@RunWith(MockitoJUnitRunner.class)
public class PrimeControllerImplTest {

    @Mock
    PrimesService primesService;
    private PrimeControllerImpl testee;

    @Before
    public void setUp() {
        testee = new PrimeControllerImpl(primesService);
    }

    @Test
    public void getPrimesResponse() {
        final List<Long> expectedPrimes = Arrays.asList(2L, 3L, 5L, 7L);
        when(primesService
                .getPrimes(anyLong()))
                .thenReturn(Primes.builder().initial(10L).primes(expectedPrimes).build());

        ResponseEntity actualResponse = testee.getPrimesResponseByLimit(10L);

        assertThat(actualResponse).isEqualToComparingFieldByField(
                ResponseEntity.ok(buildPrimeResponse(10L, expectedPrimes)));
        assertThat(actualResponse.getStatusCodeValue()).isEqualTo(200);
    }

    private PrimesResponse buildPrimeResponse(long limit, List<Long> primes) {

        return new PrimesResponse().withInitial(limit).withPrimes(primes);
    }
}
