package com.gcd.w40k.units;

import com.gcd.w40k.resolvers.wound.Wound;
import com.gcd.w40k.tableunits.Model;
import com.gcd.w40k.tableunits.Squad;
import com.gcd.w40k.tableunits.spacemarines.models.PrimarisSpaceMarine;
import com.gcd.w40k.tableunits.spacemarines.models.SpaceMarine;
import com.gcd.w40k.utils.DiceUtils;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SquadTest {
    @Test
    @DisplayName("A squad contains models")
    public void testSquadContainsModels() {
        Squad squad = createTestSquad();
        assert squad.getModels().size() == 2;
    }

    @Test
    @DisplayName("When a model of the squad is killed, the model is removed from the squad")
    public void testSquadMemberKilled() {
        Squad squad = createTestSquad();
        squad.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(2));
        squad.resolveSave(new Wound(0, 2));

        assert squad.getLivingMembers().size() == 1;
    }

    @Test
    @DisplayName("When a model of the squad is wounded, the next wound is allocated to the wounded model")
    public void testWoundAllocatedToMostWounded() {
        Squad squad = createTestSquad();
        squad.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(2));

        squad.resolveSave(new Wound(0, 1));
        assert squad.getLivingMembers().size() == 2;

        squad.resolveSave(new Wound(0, 1));
        assert squad.getLivingMembers().size() == 1;
    }

    @Test
    @DisplayName("When an extra wounds are applied to a model, the wounds are wasted")
    public void testWastedWound() {
        Squad squad = createTestSquad();
        squad.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(2));

        squad.resolveSave(new Wound(0, 50));
        assert squad.getLivingMembers().size() == 1;
        assert squad.getLivingMembers().get(0).getCurrentHitPoints() == 2;
    }

    @Test
    @DisplayName("When a save is successful, no damage is inflicted")
    public void testSuccessfulSave() {
        Squad squad = createTestSquad();
        squad.setAssignedDice(DiceUtils.mockWeightedSixFacedDice(3));

        squad.resolveSave(new Wound(0, 50));
        assert squad.getLivingMembers().size() == 2;
        for(Model squadMember : squad.getLivingMembers()) {
            assert squadMember.getCurrentHitPoints() == 2;
        }

    }

    public static Squad createTestSquad() {
        return Squad.create(PrimarisSpaceMarine.class, 2, "A test squad");
    }
}
