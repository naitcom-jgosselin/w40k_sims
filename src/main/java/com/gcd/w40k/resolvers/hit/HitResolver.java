package com.gcd.w40k.resolvers.hit;

import java.math.BigInteger;
import java.util.Arrays;

public class HitResolver {

    public static BigInteger[] getHittingResults(int shooterBallisticSkill) {
        return getHittingResults(shooterBallisticSkill, new int[] {});
    }

    public static BigInteger[] getHittingResults(int shooterBallisticSkill, int... modifiers) {
        BigInteger[] acceptableValues = new BigInteger[] {
                BigInteger.valueOf(1),
                BigInteger.valueOf(2),
                BigInteger.valueOf(3),
                BigInteger.valueOf(4),
                BigInteger.valueOf(5),
                BigInteger.valueOf(6)};

        BigInteger lowestValue = BigInteger.valueOf(shooterBallisticSkill);
        for(int modifier : modifiers) {
            lowestValue = lowestValue.add(BigInteger.valueOf(modifier));
        }

        return Arrays.copyOfRange(acceptableValues, lowestValue.intValue() - 1, acceptableValues.length);
    }
}
