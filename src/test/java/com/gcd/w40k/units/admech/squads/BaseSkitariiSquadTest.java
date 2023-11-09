package com.gcd.w40k.units.admech.squads;

import com.gcd.w40k.tableunits.Squad;
import com.gcd.w40k.tableunits.admech.models.SkitariiRanger;
import com.gcd.w40k.tableunits.spacemarines.models.SpaceMarine;
import com.gcd.w40k.utils.DiceUtils;
import org.junit.jupiter.api.Test;

public class BaseSkitariiSquadTest {
    @Test
    public void baseSkitariiSquadNominalCase() {
        Squad skitariiSquad = SkitariiRanger.getBasicSquad();
        Squad marineSquad = SpaceMarine.getBasicSquad();

        marineSquad.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(6));
        skitariiSquad.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(1));

        marineSquad.shootAt(skitariiSquad, 24);

        assert skitariiSquad.getLivingMembers().size() == 0;
    }

    @Test
    public void baseSkitariiSquadSuccessfulSaves() {
        Squad skitariiSquad = SkitariiRanger.getBasicSquad();
        Squad marineSquad = SpaceMarine.getBasicSquad();

        marineSquad.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(6));
        skitariiSquad.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(6));

        marineSquad.shootAt(skitariiSquad, 24);

        assert skitariiSquad.getLivingMembers().size() == 10;
    }
}
