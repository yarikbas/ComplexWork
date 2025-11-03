package com.org.insurance.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessObligation extends Obligation {
    private String businessType;
    private double revenue;
    private int employeeCount;

    public BusinessObligation(String name, double insuredAmount, double factor,
                              int period, double interestRate, double probability,
                              double maxCost, String businessType, double revenue, int employeeCount) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.businessType = businessType;
        this.revenue = revenue;
        this.employeeCount = employeeCount;
    }

    @Override
    public double calculatePayout() {
        return insuredAmount * probability;
    }

    @Override
    public double calculateRisk() {
        return (1 - probability) * factor * (revenue / 1000000);
    }

    @Override
    public double calculateValue() {
        return insuredAmount * interestRate * period;
    }
}