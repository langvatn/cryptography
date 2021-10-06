package no.langvatn.cryptography.domain;

public record PrimPair(long p, long q) {
    public boolean isRsa(Long m) {
        return p * q == m;
    }

    public long toRsa() {
        return p * q;
    }
}
