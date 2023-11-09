package com.gcd.w40k.dice;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    public int roll(int faceCount) {
        return ThreadLocalRandom.current().nextInt(1, faceCount+1);
    }

    public int roll() {
        return roll(6);
    }

    public static boolean filter(int rollValue, int... acceptableValues) {
        for(int acceptableValue : acceptableValues) {
            if(acceptableValue == rollValue) {
                return true;
            }
        }
        return false;
    }
}
