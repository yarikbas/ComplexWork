package com.org.insurance.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HealthObligation extends Obligation {
    private String coverageType;
    private boolean hasPreExistingConditions;
    private double annualLimit;

    public HealthObligation(String name, double insuredAmount, double factor,
                            int period, double interestRate, double probability,
                            double maxCost, String coverageType, boolean hasPreExistingConditions, double annualLimit) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.coverageType = coverageType;
        this.hasPreExistingConditions = hasPreExistingConditions;
        this.annualLimit = annualLimit;
    }

    @Override
    public double calculatePayout() {
        double conditionMultiplier = hasPreExistingConditions ? 0.7 : 1.0;
        return Math.min(insuredAmount, annualLimit) * probability * conditionMultiplier;
    }

    @Override
    public double calculateRisk() {
        double conditionRisk = hasPreExistingConditions ? 1.8 : 1.0;
        return (1 - probability) * factor * conditionRisk;
    }

    @Override
    public double calculateValue() {
        return Math.min(insuredAmount, annualLimit) * interestRate * period;
    }
}
