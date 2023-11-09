package com.gcd.w40k.tableunits.admech.models;

import com.gcd.w40k.equipment.Weapon;
import com.gcd.w40k.resolvers.save.Save;
import com.gcd.w40k.tableunits.Model;
import com.gcd.w40k.tableunits.Squad;
import com.gcd.w40k.tableunits.admech.equipment.GalvanicRifle;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class SkitariiRanger extends Model {
    public static Squad getBasicSquad() {
        return Squad.create(SkitariiRanger.class, 10, "A basic 10 man skitarii ranger squad");
    }

    @Override
    public String getDescription() {
        return "A skitarii ranger";
    }

    @Override
    public int getWeaponSkill() {
        return 4;
    }

    @Override
    public int getBallisticSkill() {
        return 3;
    }

    @Override
    public int getToughness() {
        return 3;
    }

    @Override
    public Save getSave() {
        return Save.builder()
                .withNormalSave(4)
                .withInvulnerableSave(6)
                .build();
    }

    @Override
    protected Weapon[] getRangedWeapons() {
        return new Weapon[] {new GalvanicRifle(this)};
    }

    @Override
    public int getMaximumHitPoints() {
        return 1;
    }

    @Override
    public int getPointCost() {
        return 7;
    }
}
