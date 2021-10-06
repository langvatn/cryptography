package no.langvatn.cryptography.functions;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class GenerateAllPrimeNumbersForRsaFunction implements Function<Long, List<Long>> {

    private final IsPrimeNumberFunction isPrimeNumberFunction = new IsPrimeNumberFunction();

    @Override
    public List<Long> apply(Long m) {
        System.out.println("Generating all possible prime numbers up to RSA where m=" + m + ".");
        return LongStream.rangeClosed(2, m / 2)
                .filter(value -> {
                    if (value % 1_000_000 == 0)
                        System.out.println(Math.round((double) value / ((double) m / 2) * 100) + "%");
                    return isPrimeNumberFunction.apply(value);
                })
                .boxed()
                .collect(Collectors.toList());
    }
}
