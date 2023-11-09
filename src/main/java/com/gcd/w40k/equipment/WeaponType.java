package com.gcd.w40k.equipment;

public abstract class WeaponType {
    protected int shots;
    public abstract int resolveShotCount(Weapon weapon, int range);

    public static WeaponType rapidFire(int shots) {
        return new RapidFire(shots);
    }
}
