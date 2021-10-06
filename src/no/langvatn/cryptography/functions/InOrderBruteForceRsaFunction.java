package no.langvatn.cryptography.functions;

import no.langvatn.cryptography.domain.PrimPair;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InOrderBruteForceRsaFunction implements Function<Long, PrimPair> {
    private final GenerateAllPrimeNumbersForRsaFunction generateAllPrimeNumbersForRsaFunction = new GenerateAllPrimeNumbersForRsaFunction();

    @Override
    public PrimPair apply(Long m) {
        var start = LocalDateTime.now();

        var inOrder = new InOrder(generateAllPrimeNumbersForRsaFunction.apply(m));

        System.out.println(start + " - In order brute force m=" + m + "...");

        PrimPair next = inOrder.next();


        var count = 1L;
        while (!next.isRsa(m)) {
            count++;
            if (count % 1_000_000 == 0) {
                System.out.println(LocalDateTime.now() + " - In order Brute force count (" + Math.round((double) count / ((double) inOrder.possibilities()) * 100) + "%" + " )");
            }
            next = inOrder.next();
        }

        var end = LocalDateTime.now();
        System.out.println(end + " - Cracked m=" + m + " where p=" + next.p() + " and q=" + next.q() + ". (Trys: " + count + "/" + inOrder.possibilities() + ")");
        return next;
    }

    private class InOrder {
        private final List<Long> possibilities;
        private final Iterator<Long> pIterator;
        private Iterator<Long> qIterator;
        private Long p;
        private Long q;


        public InOrder(List<Long> possibilities) {
            System.out.println("Number of combinations: " + Math.pow(possibilities.size(), 2) + ".");
            this.possibilities = possibilities;
            pIterator = possibilities.iterator();
            qIterator = possibilities.iterator();
        }

        public PrimPair next() {
            if (!qIterator.hasNext()) {
                if (!pIterator.hasNext()) {
                    throw new RuntimeException("No more combinations of 'p' and 'q'.");
                }
                p = pIterator.next();
                qIterator = possibilities.iterator();
            }

            if (p == null) {
                p = pIterator.next();
            }

            q = qIterator.next();
            return new PrimPair(p, q);
        }

        public long remainAttempts() {
            return listOfIterator(pIterator).size() - 1 + listOfIterator(qIterator).size();

        }

        public long possibilities() {
            return (long) Math.pow(possibilities.size(), 2);
        }

        private <T> List<T> listOfIterator(Iterator<T> iterator) {
            return StreamSupport
                    .stream(((Iterable<T>) () -> iterator).spliterator(), true)
                    .collect(Collectors.toList());
        }


    }

}
