package no.langvatn.cryptography.functions;

import no.langvatn.cryptography.domain.PrimPair;

import java.util.function.Function;

public class GenerateRandomPrimPairFunction implements Function<Long, PrimPair> {
    private final GenerateRandomPrimeNumberFunction generateRandomPrimeNumberFunction = new GenerateRandomPrimeNumberFunction();

    @Override
    public PrimPair apply(Long max) {
        return new PrimPair(
                generateRandomPrimeNumberFunction.apply(max),
                generateRandomPrimeNumberFunction.apply(max)
        );
    }
}