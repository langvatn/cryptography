package no.langvatn.cryptography.functions;

import java.util.function.Function;

public class IsPrimeNumberFunction implements Function<Long, Boolean> {
    @Override
    public Boolean apply(Long n) {
        if (n == 2 || n == 3) {
            return true;
        }
        if (n <= 1 || n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
