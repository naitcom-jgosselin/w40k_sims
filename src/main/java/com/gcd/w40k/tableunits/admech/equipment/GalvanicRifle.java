package com.gcd.w40k.tableunits.admech.equipment;

import com.gcd.w40k.equipment.Weapon;
import com.gcd.w40k.equipment.WeaponType;
import com.gcd.w40k.resolvers.hit.Hit;
import com.gcd.w40k.resolvers.wound.Wound;
import com.gcd.w40k.resolvers.wound.WoundResolver;
import com.gcd.w40k.tableunits.Model;
import com.gcd.w40k.tableunits.NoLivingModelException;
import com.gcd.w40k.tableunits.Squad;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalvanicRifle extends Weapon {
    public GalvanicRifle(Model owner) {
        super(owner);
    }

    @Override
    public int getRange() {
        return 30;
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

    @Override
    public List<Wound> resolveWounds(Model model, Squad target, Hit hit) {
        List<Wound> wounds = new ArrayList<>();
        try {
            BigInteger[] acceptableWoundResults = WoundResolver.getWoundingResults(getStrength(),
                    target.getMostWoundedModel().getToughness());

            BigInteger diceResult = BigInteger.valueOf(model.getAssignedDice().roll());
            if(diceResult.equals(BigInteger.valueOf(6))) {
                wounds.add(new Wound(-1,1));
            } else if(Arrays.asList(acceptableWoundResults).contains(diceResult)) {
                wounds.add(Wound.standard());
            }
        } catch (NoLivingModelException ex) {
            //swallow exceptions
        }

        return wounds;
    }
}
