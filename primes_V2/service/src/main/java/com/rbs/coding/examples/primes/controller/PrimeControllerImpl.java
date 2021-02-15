package com.rbs.coding.examples.primes.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.rbs.coding.examples.primes.api.PrimeController;
import com.rbs.coding.examples.primes.api.model.PrimesResponse;
import com.rbs.coding.examples.primes.model.Primes;
import com.rbs.coding.examples.primes.service.PrimesService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PrimeControllerImpl implements PrimeController {

    private PrimesService primesService;

    @Override
    @RequestMapping(value = "/{limit}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PrimesResponse> getPrimesResponseByLimit(@Min(2L) @Max(1000000L) Long limit) {
        Primes primes = primesService.getPrimes(limit);
        return ResponseEntity.ok(new PrimesResponse().withInitial(primes.getInitial()).withPrimes(primes.getPrimes()));
    }
}