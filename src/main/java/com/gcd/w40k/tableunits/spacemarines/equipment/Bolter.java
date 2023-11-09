package com.gcd.w40k.tableunits.spacemarines.equipment;

import com.gcd.w40k.equipment.Weapon;
import com.gcd.w40k.equipment.WeaponType;
import com.gcd.w40k.tableunits.Model;

public class Bolter extends Weapon {
    public Bolter(Model owner) {
        super(owner);
    }

    @Override
    public int getRange() {
        return 24;
    }

    @Override
    public int getStrength() {
        return 4;
    }

    @Override
    public int getAp() {
        return 0;
    }

    @Override
    public int getDamage() {
        return 1;
    }

    @Override
    public WeaponType getWeaponType() {
        return WeaponType.rapidFire(1);
    }
}
