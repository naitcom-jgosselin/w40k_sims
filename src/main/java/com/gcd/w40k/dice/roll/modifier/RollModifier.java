package com.gcd.w40k.dice.roll.modifier;

import com.gcd.w40k.dice.Dice;

public interface RollModifier {
    int apply(Dice dice);
}
