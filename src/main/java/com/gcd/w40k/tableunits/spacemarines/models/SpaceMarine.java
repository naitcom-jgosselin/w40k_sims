package com.gcd.w40k.tableunits.spacemarines.models;

import com.gcd.w40k.equipment.Weapon;
import com.gcd.w40k.resolvers.save.Save;
import com.gcd.w40k.tableunits.Model;
import com.gcd.w40k.tableunits.Squad;
import com.gcd.w40k.tableunits.spacemarines.equipment.Bolter;

public class SpaceMarine extends Model {

    public static Squad getBasicSquad() {
        return Squad.create(SpaceMarine.class, 10, "A base 10 man space marine squad");
    }

    @Override
    public String getDescription() {
        return "A basic space marine";
    }

    @Override
    public int getWeaponSkill() {
        return 3;
    }

    @Override
    public int getBallisticSkill() {
        return 3;
    }

    @Override
    public int getToughness() {
        return 4;
    }

    @Override
    public int getMaximumHitPoints() {
        return 1;
    }

    @Override
    public Save getSave() {
        return Save.builder().withNormalSave(3).build();
    }

    @Override
    protected Weapon[] getRangedWeapons() {
        return new Weapon[] {new Bolter(this)};
    }

    @Override
    public int getPointCost() {
        return 13;
    }
}
