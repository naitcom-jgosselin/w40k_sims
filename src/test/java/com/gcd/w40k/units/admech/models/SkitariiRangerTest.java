package com.gcd.w40k.units.admech.models;

import com.gcd.w40k.tableunits.NoLivingModelException;
import com.gcd.w40k.tableunits.Squad;
import com.gcd.w40k.tableunits.admech.models.SkitariiRanger;
import com.gcd.w40k.units.SquadTest;
import com.gcd.w40k.utils.DiceUtils;
import org.junit.jupiter.api.Test;

public class SkitariiRangerTest {

    @Test
    public void nominalCase() throws NoLivingModelException {
        SkitariiRanger ranger = getRanger();
        Squad target = SquadTest.createTestSquad();
        target.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(1));

        ranger.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(5));
        ranger.resolveRangedPhase(target, 30);

        assert target.getMostWoundedModel().getCurrentHitPoints() == 1;
        assert target.getLivingMembers().size() == 2;
    }

    @Test
    public void testPiercingWound() throws NoLivingModelException {
        SkitariiRanger ranger = getRanger();
        Squad target = SquadTest.createTestSquad();

        // skitarii should only roll 6, triggering a AP -1 wound
        // target should only roll 3, which would be enough to save a normal wound, but not a AP - 1 wound
        target.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(3));
        ranger.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(6));

        ranger.resolveRangedPhase(target, 30);

        assert target.getMostWoundedModel().getCurrentHitPoints() == 1;
        assert target.getLivingMembers().size() == 2;
    }

    @Test
    public void testShortRange() {
        SkitariiRanger ranger = getRanger();
        Squad target = SquadTest.createTestSquad();
        target.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(1));

        ranger.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(5));
        ranger.resolveRangedPhase(target, 15);

        assert target.getLivingMembers().size() == 1;
    }

    @Test
    public void testOutOfRange() throws NoLivingModelException {
        SkitariiRanger ranger = getRanger();
        Squad target = SquadTest.createTestSquad();
        target.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(1));

        ranger.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(5));
        ranger.resolveRangedPhase(target, 34);

        assert target.getMostWoundedModel().getCurrentHitPoints() == 2;
        assert target.getLivingMembers().size() == 2;
    }

    private SkitariiRanger getRanger() {
        return new SkitariiRanger();
    }
}
