package com.org.insurance.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LiabilityObligation extends Obligation {
    private double limitPerClaim;
    private double aggregateLimit;

    public LiabilityObligation(String name, double insuredAmount, double factor,
                               int period, double interestRate, double probability,
                               double maxCost, double limitPerClaim, double aggregateLimit) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.limitPerClaim = limitPerClaim;
        this.aggregateLimit = aggregateLimit;
    }

    @Override
    public double calculatePayout() {
        return Math.min(limitPerClaim, insuredAmount) * probability;
    }

    @Override
    public double calculateRisk() {
        return (1 - probability) * factor * (limitPerClaim / 100000);
    }

    @Override
    public double calculateValue() {
        return Math.min(limitPerClaim, aggregateLimit) * interestRate * period;
    }
}