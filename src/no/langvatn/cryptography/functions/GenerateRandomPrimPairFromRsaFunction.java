package no.langvatn.cryptography.functions;

import no.langvatn.cryptography.domain.PrimPair;

import java.util.function.Function;

public class GenerateRandomPrimPairFromRsaFunction implements Function<Long, PrimPair> {
    private final GenerateRandomPrimeNumberFunction generateRandomPrimeNumberFunction = new GenerateRandomPrimeNumberFunction();

    @Override
    public PrimPair apply(Long m) {
        return new PrimPair(
                generateRandomPrimeNumberFunction.apply(m / 2),
                generateRandomPrimeNumberFunction.apply(m / 2)
        );
    }
}