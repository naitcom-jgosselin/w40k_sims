package com.gcd.w40k.simulations;

import com.gcd.w40k.tableunits.Squad;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Score {
    Squad testedSquad;
    BigDecimal offensiveShootingPoints;
    BigDecimal defensiveShootingPoints;

    public Score(Squad testedSquad, BigDecimal offensiveShootingPoints, BigDecimal defensiveShootingPoints) {
        this.testedSquad = testedSquad;
        this.offensiveShootingPoints = offensiveShootingPoints;
        this.defensiveShootingPoints = defensiveShootingPoints;
    }

    public BigDecimal getOffensiveShootingPoints() {
        return offensiveShootingPoints;
    }

    public BigDecimal getDefensiveShootingPoints() {
        return defensiveShootingPoints;
    }

    public BigDecimal calculateRentabilityQuotient() {
        BigDecimal totalPoints = offensiveShootingPoints.add(defensiveShootingPoints);
        return totalPoints.divide(testedSquad.getTotalPointCost(), RoundingMode.HALF_UP);
    }
}
