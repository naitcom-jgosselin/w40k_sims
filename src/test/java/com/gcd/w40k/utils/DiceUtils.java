package com.gcd.w40k.utils;

import com.gcd.w40k.dice.Dice;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiceUtils {
    // gets a set of 6 dices that will roll every possible result
    public static List<Dice> getMockedDiceSet() {
        ArrayList<Dice> dices = new ArrayList<>();
        for(int i = 1; i<7; i++) {
            dices.add(mockWeightedSixFacedDice(i));
        }
        return dices;
    }

    // This is for cheating during the tests (to test what happens given specific results)
    public static Dice mockWeightedSixFacedDice(int expectedResult) {
        Dice mockedDice = mock(Dice.class);
        when(mockedDice.roll()).thenReturn(expectedResult);
        return mockedDice;
    }
}
