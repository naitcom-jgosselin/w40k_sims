package com.gcd.w40k.tableunits;

import com.gcd.w40k.dice.Dice;
import com.gcd.w40k.equipment.Weapon;
import com.gcd.w40k.resolvers.hit.Hit;
import com.gcd.w40k.resolvers.save.Save;
import com.gcd.w40k.resolvers.save.SaveResolver;
import com.gcd.w40k.resolvers.wound.Wound;

import java.util.List;

public abstract class Model {

    protected Dice assignedDice = new Dice();
    protected Integer currentHitPoints;

    public abstract String getDescription();
    public abstract int getWeaponSkill();
    public abstract int getBallisticSkill();
    public abstract int getToughness();
    public abstract int getMaximumHitPoints();
    public abstract Save getSave();

    protected abstract Weapon[] getRangedWeapons();

    public abstract int getPointCost();

    // This method might seem like a unneeded complexity, but is actually used for testing
    // When we want to computeScore a given dice result, we just mock the dice we want with mockito
    public void setAssignedDice(Dice dice) {
        this.assignedDice = dice;
    }

    public Dice getAssignedDice() {
        return assignedDice;
    }

    public void resolveRangedPhase(Squad target, int range) {
        for(Weapon rangedWeapon : getRangedWeapons()) {
            List<Hit> hits = rangedWeapon.resolveHits(this, target, range);
            for(Hit weaponHit : hits) {
                List<Wound> wounds = rangedWeapon.resolveWounds(this, target, weaponHit);
                for(Wound wound : wounds) {
                    target.resolveSave(wound);
                }
            }
        }
    }

    public void resolveSave(Wound wound) {
        int[] unsavedOutcomes = SaveResolver.getUnsavedOutcomes(wound, getSave());

        boolean isUnsaved = Dice.filter(assignedDice.roll(), unsavedOutcomes);

        if(isUnsaved) {
            allocateWound(wound);
        }
    }

    public int getCurrentHitPoints() {
        if(null == currentHitPoints) {
            currentHitPoints = getMaximumHitPoints();
        }
        return currentHitPoints;
    }

    protected void allocateWound(Wound wound) {
        setCurrentHitPoints(getCurrentHitPoints() - wound.getDamage());
    }

    private void setCurrentHitPoints(int hitPoints) {
        currentHitPoints = hitPoints;
    }

    public void restore() {
        this.currentHitPoints = getMaximumHitPoints();
    }
}
