package com.gcd.w40k.simulators;

import com.gcd.w40k.simulations.RealisticSimulator;
import com.gcd.w40k.simulations.Score;
import com.gcd.w40k.tableunits.admech.models.SkitariiRanger;
import com.gcd.w40k.tableunits.spacemarines.models.SpaceMarine;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class RealisticSimulatorTest {
    @Test
    public void nominalCase() {
        RealisticSimulator simulator = new RealisticSimulator(SkitariiRanger.getBasicSquad());
        RealisticSimulator simulator2 = new RealisticSimulator(SpaceMarine.getBasicSquad());

        Score resultsSkitarii = simulator.computeScore();
        Score resultsSpaceMarine = simulator2.computeScore();

        BigDecimal offensiveScoreSkitarii = resultsSkitarii.getOffensiveShootingPoints();
        BigDecimal defensiveScoreSkitarii = resultsSkitarii.getDefensiveShootingPoints();

        BigDecimal offensiveScoreSpaceMarine = resultsSpaceMarine.getOffensiveShootingPoints();
        BigDecimal defensiveScoreSpaceMarine = resultsSpaceMarine.getDefensiveShootingPoints();

        // Space marines should be tankier on average than skitarii
        assert defensiveScoreSpaceMarine.compareTo(defensiveScoreSkitarii) > 0;

        // Skitarii deal a bit more damage on average than space marines
        assert offensiveScoreSpaceMarine.compareTo(offensiveScoreSkitarii) > 0;
    }
}
