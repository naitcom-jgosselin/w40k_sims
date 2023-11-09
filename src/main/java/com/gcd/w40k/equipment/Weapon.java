package com.gcd.w40k.equipment;

import com.gcd.w40k.resolvers.hit.Hit;
import com.gcd.w40k.resolvers.hit.HitResolver;
import com.gcd.w40k.resolvers.wound.Wound;
import com.gcd.w40k.resolvers.wound.WoundResolver;
import com.gcd.w40k.tableunits.Model;
import com.gcd.w40k.tableunits.NoLivingModelException;
import com.gcd.w40k.tableunits.Squad;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Weapon {

    Model owner;

    public Weapon(Model owner) {
        this.owner = owner;
    }

    public abstract int getRange();
    public abstract int getStrength();
    public abstract int getAp();
    public abstract int getDamage();

    public abstract WeaponType getWeaponType();

    public List<Hit> resolveHits(Model model, Squad target, int range) {
        List<Hit> successfulHits = new ArrayList<>();

        if(target.getLivingMembers().size() > 0) {
            int shots = getWeaponType().resolveShotCount(this, range);

            BigInteger[] acceptableHitResults = HitResolver.getHittingResults(model.getBallisticSkill());

            for (int i = 0; i < shots; i++) {
                int diceResult = model.getAssignedDice().roll();
                if (Arrays.asList(acceptableHitResults).contains(BigInteger.valueOf(diceResult))) {
                    successfulHits.add(new Hit(getStrength()));
                }
            }
        }
        return successfulHits;
    }

    public List<Wound> resolveWounds(Model model, Squad target, Hit hit) {
        List<Wound> wounds = new ArrayList<>();
        try {
            BigInteger[] acceptableWoundResults = WoundResolver.getWoundingResults(getStrength(),
                    target.getMostWoundedModel().getToughness());

            BigInteger diceResult = BigInteger.valueOf(model.getAssignedDice().roll());
            if(Arrays.asList(acceptableWoundResults).contains(diceResult)) {
                wounds.add(new Wound(getAp(), getDamage()));
            }
        } catch (NoLivingModelException ex) {
            // swallow exception
        }

        return wounds;
    }
}
