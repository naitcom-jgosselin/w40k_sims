package com.gcd.w40k.resolvers;

import com.gcd.w40k.resolvers.hit.HitResolver;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

public class HitResolverTest {
    @Test
    public void testNominal() {
        assert Arrays.equals(HitResolver.getHittingResults(3),
                new BigInteger[] {BigInteger.valueOf(3),
                        BigInteger.valueOf(4),
                        BigInteger.valueOf(5),
                        BigInteger.valueOf(6)});
    }

    @Test
    public void testModifier() {
        BigInteger[] hitResults = HitResolver.getHittingResults(5, 2);
        assert Arrays.equals(hitResults, new BigInteger[]{});
    }
}