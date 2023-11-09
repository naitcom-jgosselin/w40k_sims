package com.gcd.w40k.simulations;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimulationResults {
    BigDecimal totalUnsavedWounds;
    BigDecimal average;
    BigDecimal score;

    public SimulationResults(int unsavedWounds, int simCount, int pointCost) {
        this.totalUnsavedWounds = new BigDecimal(String.valueOf(unsavedWounds));
        this.average = totalUnsavedWounds.divide(new BigDecimal(String.valueOf(simCount)),
                4,
                RoundingMode.HALF_UP);
        this.score = average.multiply(new BigDecimal(String.valueOf(1000)))
                .divide(new BigDecimal(String.valueOf(pointCost)),
                        4,
                        RoundingMode.HALF_UP);
    }
}
