package com.gcd.w40k.simulations;

import com.gcd.w40k.tableunits.Squad;
import com.gcd.w40k.tableunits.spacemarines.models.SpaceMarine;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * This class will estimate the unit score using these objective criterias :
 *  - Osp : How much shooting phases on average does the unit needs to wipe out a 10 man space marine squad
 *  - Dsp : How much shooting phases on average can the unit survive against a 10 man space marine squad
 *  - Ocp : How much combat phases on average does the unit needs to wipe out a 10 man space marine squad
 *  - Dcp : How much combat phases on average can the unit survive against a 10 man space marine squad
 *
 *  The score formula :
 *  ((Osp / Dsp + Ocp / Dcp) * Move-speed * (1 + estimated special rules value)) / point cost
 */
public class RealisticSimulator {

    private static final int SIMULATION_COUNT = 100000;

    Squad squad;
    Squad spaceMarineSquad = SpaceMarine.getBasicSquad();

    public RealisticSimulator(Squad squad) {
        this.squad = squad;
    }

    public Score computeScore() {
        BigDecimal offensiveShootingPhase = simulateOffensiveShootingPhase();
        BigDecimal defensiveShootingPhase = simulateDefensiveShootingPhase();

        return new Score(squad, offensiveShootingPhase, defensiveShootingPhase);
    }

    private BigDecimal simulateOffensiveShootingPhase() {
        int shootingPhaseCount = 0;
        for(int i = 0; i<SIMULATION_COUNT; i++) {
            spaceMarineSquad.restore();
            squad.restore();
            while(spaceMarineSquad.getLivingMembers().size() > 0) {
                shootingPhaseCount++;
                squad.shootAt(spaceMarineSquad, 3);
            }
        }
        return BigDecimal.valueOf(shootingPhaseCount).divide(BigDecimal.valueOf(SIMULATION_COUNT))
                .setScale(2, RoundingMode.FLOOR);
    }

    private BigDecimal simulateDefensiveShootingPhase() {
        int shootingPhaseCount = 0;
        for(int i = 0; i<SIMULATION_COUNT; i++) {
            spaceMarineSquad.restore();
            squad.restore();
            while(squad.getLivingMembers().size() > 0) {
                shootingPhaseCount++;
                spaceMarineSquad.shootAt(squad, 3);
            }
        }
        return BigDecimal.valueOf(shootingPhaseCount).divide(BigDecimal.valueOf(SIMULATION_COUNT))
                .setScale(2, RoundingMode.FLOOR);
    }
}
