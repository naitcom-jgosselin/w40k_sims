package com.gcd.w40k.units.spacemarines.models;

import com.gcd.w40k.tableunits.NoLivingModelException;
import com.gcd.w40k.tableunits.Squad;
import com.gcd.w40k.tableunits.spacemarines.models.SpaceMarine;
import com.gcd.w40k.units.SquadTest;
import com.gcd.w40k.utils.DiceUtils;
import org.junit.jupiter.api.Test;

public class SpaceMarineTest {
    @Test
    public void nominalCase() throws NoLivingModelException {
        SpaceMarine marine = getSpaceMarine();
        Squad target = SquadTest.createTestSquad();
        target.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(1));

        marine.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(5));
        marine.resolveRangedPhase(target, 24);

        assert target.getMostWoundedModel().getCurrentHitPoints() == 1;
        assert target.getLivingMembers().size() == 2;
    }

    @Test
    public void testShortRange() {
        SpaceMarine marine = getSpaceMarine();
        Squad target = SquadTest.createTestSquad();
        target.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(1));

        marine.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(5));
        marine.resolveRangedPhase(target, 12);

        assert target.getLivingMembers().size() == 1;
    }

    @Test
    public void testOutOfRange() throws NoLivingModelException {
        SpaceMarine marine = getSpaceMarine();
        Squad target = SquadTest.createTestSquad();
        target.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(1));

        marine.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(5));
        marine.resolveRangedPhase(target, 34);

        assert target.getMostWoundedModel().getCurrentHitPoints() == 2;
        assert target.getLivingMembers().size() == 2;
    }

    private SpaceMarine getSpaceMarine() {
        return new SpaceMarine();
    }
}
