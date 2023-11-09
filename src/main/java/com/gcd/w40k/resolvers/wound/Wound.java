package com.gcd.w40k.resolvers.wound;

import java.util.Objects;

public class Wound {
    int apModifier;
    int damage;

    public Wound(int apModifier, int damage) {
        this.apModifier = apModifier;
        this.damage = damage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wound wound = (Wound) o;
        return apModifier == wound.apModifier &&
                damage == wound.damage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(apModifier, damage);
    }

    public int getApModifier() {
        return apModifier;
    }

    public int getDamage() {
        return damage;
    }

    public static Wound standard() {
        return new Wound(0, 1);
    }
}
