package com.gcd.w40k.dice.roll.modifier;

import com.gcd.w40k.dice.Dice;

public class BonusModifier implements RollModifier{

    int bonus;

    public BonusModifier(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public int apply(Dice dice) {
        return dice.roll() + bonus;
    }
}
