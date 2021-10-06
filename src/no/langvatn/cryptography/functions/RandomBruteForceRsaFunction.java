package no.langvatn.cryptography.functions;

import no.langvatn.cryptography.domain.PrimPair;

import java.time.LocalDateTime;
import java.util.function.Function;

public class RandomBruteForceRsaFunction implements Function<Long, PrimPair> {
    private final GenerateRandomPrimPairFromRsaFunction generateRandomPrimPairFromRsaFunction = new GenerateRandomPrimPairFromRsaFunction();

    @Override
    public PrimPair apply(Long m) {
        var start = LocalDateTime.now();
        System.out.println(start + " - Random brute force m=" + m + "...");
        var pair = generateRandomPrimPairFromRsaFunction.apply(m);


        var count = 1L;
        while (!pair.isRsa(m)) {
            pair = generateRandomPrimPairFromRsaFunction.apply(m);
            count++;
            if(count % 100_000 == 0) {
                System.out.println(LocalDateTime.now() + " - Brute force count (" + count + ")");
            }

        }

        var end = LocalDateTime.now();
        System.out.println(end + " - Cracked m=" + m + " where p=" + pair.p() + " and q=" + pair.q() + ". (Number for trys: " + count + ")");
        return pair;
    }
}
