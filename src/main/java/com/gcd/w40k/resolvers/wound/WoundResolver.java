package com.gcd.w40k.resolvers.wound;

import java.math.BigInteger;
import java.util.Arrays;

public class WoundResolver {
    public static BigInteger[] getWoundingResults(int attackerStrength, int defenderToughness) {
        BigInteger[] possibleValues = new BigInteger[] {BigInteger.valueOf(2),
                BigInteger.valueOf(3),
                BigInteger.valueOf(4),
                BigInteger.valueOf(5),
                BigInteger.valueOf(6)};

        if(attackerStrength == defenderToughness) {
            return Arrays.copyOfRange(possibleValues, 2, possibleValues.length);
        } else if (attackerStrength > defenderToughness) {
            if(attackerStrength >= defenderToughness * 2) {
                return possibleValues;
            } else {
                return Arrays.copyOfRange(possibleValues, 1, possibleValues.length);
            }
        } else {
            if (attackerStrength * 2 <= defenderToughness) {
                return Arrays.copyOfRange(possibleValues, 4, possibleValues.length);
            } else {
                return Arrays.copyOfRange(possibleValues, 3, possibleValues.length);
            }
        }
    }
}
