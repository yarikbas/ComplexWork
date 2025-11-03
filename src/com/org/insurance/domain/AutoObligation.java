package com.org.insurance.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AutoObligation extends Obligation {
    private String vehicleType;
    private String driverClass;
    private double bonusMalus;

    public AutoObligation(String name, double insuredAmount, double factor,
                          int period, double interestRate, double probability,
                          double maxCost, String vehicleType, String driverClass, double bonusMalus) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.vehicleType = vehicleType;
        this.driverClass = driverClass;
        this.bonusMalus = bonusMalus;
    }

    @Override
    public double calculatePayout() {
        return insuredAmount * probability * bonusMalus;
    }

    @Override
    public double calculateRisk() {
        double driverRisk = "A".equals(driverClass) ? 0.8 : 1.2;
        return (1 - probability) * factor * driverRisk;
    }

    @Override
    public double calculateValue() {
        return insuredAmount * interestRate * period * bonusMalus;
    }
}
