package com.gcd.w40k.resolvers.hit;

import java.util.Objects;

public class Hit {
    int strength;

    public Hit(int strength) {
        this.strength = strength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hit hit = (Hit) o;
        return strength == hit.strength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strength);
    }
}
