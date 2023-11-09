package com.gcd.w40k.resolvers;

import com.gcd.w40k.resolvers.wound.WoundResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

public class WoundResolverTest {

    @Test
    @DisplayName("Vastly superior S vs T should wound on 2+")
    public void testVastlySuperiorStrength() {
        BigInteger[] woundingHits = WoundResolver.getWoundingResults(9,4);

        assert Arrays.equals(woundingHits, new BigInteger[]{BigInteger.valueOf(2),
                BigInteger.valueOf(3),
                BigInteger.valueOf(4),
                BigInteger.valueOf(5),
                BigInteger.valueOf(6)});
    }

    @Test
    @DisplayName("Superior S vs T should wound on 3+")
    public void testSuperiorStrength() {
        BigInteger[] woundingHits = WoundResolver.getWoundingResults(6,4);

        assert Arrays.equals(woundingHits, new BigInteger[]{BigInteger.valueOf(3),
            BigInteger.valueOf(4),
            BigInteger.valueOf(5),
            BigInteger.valueOf(6)});
}

    @Test
    @DisplayName("Equal S vs T should wound on 4+")
    public void testEqualStrengthAndToughness() {
        BigInteger[] woundingHits = WoundResolver.getWoundingResults(4,4);

        assert Arrays.equals(woundingHits, new BigInteger[] {BigInteger.valueOf(4),
                BigInteger.valueOf(5),
                BigInteger.valueOf(6)});
    }

    @Test
    @DisplayName("Inferior S vs T should wound on 5+")
    public void testInferiorStrength() {
        BigInteger[] woundingHits = WoundResolver.getWoundingResults(3,4);

        assert Arrays.equals(woundingHits, new BigInteger[] {BigInteger.valueOf(5),
                BigInteger.valueOf(6)});
    }

    @Test
    @DisplayName("Vastly inferior S vs T should wound on 6+")
    public void testVastlyInferiorStrength() {
        BigInteger[] woundingHits = WoundResolver.getWoundingResults(3,6);

        assert Arrays.equals(woundingHits, new BigInteger[]{BigInteger.valueOf(6)});
    }

}
