package no.langvatn.cryptography;

import no.langvatn.cryptography.functions.InOrderBruteForceRsaFunction;
import no.langvatn.cryptography.functions.RandomBruteForceRsaFunction;
import no.langvatn.cryptography.functions.GenerateRandomPrimPairFunction;

public class Main {

    public static void main(String[] args) {
//        GenerateRandomPrimPairFunction generateRandomPrimPairFunction = new GenerateRandomPrimPairFunction();
//
//        var pair = generateRandomPrimPairFunction.apply(100_000L);


        var bruteForce = new InOrderBruteForceRsaFunction();
        var m = 229652417L;
        bruteForce.apply(m);
    }
}
