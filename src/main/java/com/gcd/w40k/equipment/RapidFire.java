package com.gcd.w40k.equipment;

public class RapidFire extends WeaponType {

    protected RapidFire(int shots) {
        this.shots = shots;
    }

    @Override
    public int resolveShotCount(Weapon weapon, int range) {
        if(range > weapon.getRange()) {
            return 0;
        } else if(range <= weapon.getRange() / 2) {
            return shots * 2;
        }
        return shots;
    }
}
