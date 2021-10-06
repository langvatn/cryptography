package no.langvatn.cryptography.functions;

import java.util.Random;
import java.util.function.Function;

public class GenerateRandomPrimeNumberFunction implements Function<Long, Long> {
    private final Random random = new Random();
    private final IsPrimeNumberFunction isPrimeNumberFunction = new IsPrimeNumberFunction();

    @Override
    public Long apply(Long max) {
        var number = random.nextLong(max) + 1;
        while (!isPrimeNumberFunction.apply(number)) {
            number = random.nextLong(max) + 1;
        }
        return number;
    }
}
