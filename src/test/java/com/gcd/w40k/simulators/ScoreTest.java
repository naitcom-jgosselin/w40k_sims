package com.gcd.w40k.simulators;

import com.gcd.w40k.simulations.RealisticSimulator;
import com.gcd.w40k.simulations.Score;
import com.gcd.w40k.tableunits.admech.models.SkitariiRanger;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    @Test
    public void scoreTest(){
        RealisticSimulator simulator = new RealisticSimulator(SkitariiRanger.getBasicSquad());

        Score score = simulator.computeScore();

        assert score.calculateRentabilityQuotient() != null;
    }
}
