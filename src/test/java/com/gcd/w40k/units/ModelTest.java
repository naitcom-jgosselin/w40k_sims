package com.gcd.w40k.units;

import com.gcd.w40k.equipment.Weapon;
import com.gcd.w40k.resolvers.save.Save;
import com.gcd.w40k.resolvers.wound.Wound;
import com.gcd.w40k.tableunits.Model;
import com.gcd.w40k.utils.DiceUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ModelTest {
    @Test
    @DisplayName("When a model fails to save a wound, its hit points are reduced")
    public void handleWound() {
        Model model = createModel();
        model.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(1));
        model.resolveSave(Wound.standard());

        assert model.getCurrentHitPoints() == 1;
    }

    public static Model createModel() {
        return new Model() {
            @Override
            public String getDescription() {
                return "A test model";
            }

            @Override
            public int getWeaponSkill() {
                return 0;
            }

            @Override
            public int getBallisticSkill() {
                return 0;
            }

            @Override
            public int getToughness() {
                return 4;
            }

            @Override
            public Save getSave() {
                return Save.builder().withNormalSave(3).build();
            }

            @Override
            protected Weapon[] getRangedWeapons() {
                return new Weapon[0];
            }

            @Override
            public int getMaximumHitPoints() {
                return 2;
            }

            @Override
            public int getPointCost() {
                return 15;
            }
        };
    }
}
