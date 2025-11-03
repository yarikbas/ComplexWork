package com.org.insurance.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LifeObligation extends Obligation {
    private int age;
    private int termYears;

    public LifeObligation(String name, double insuredAmount, double factor,
                          int period, double interestRate, double probability,
                          double maxCost, int age, int termYears) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.age = age;
        this.termYears = termYears;
    }

    @Override
    public double calculatePayout() {
        return insuredAmount * probability * (termYears / 10.0);
    }

    @Override
    public double calculateRisk() {
        double ageRisk = age > 60 ? 1.5 : 1.0;
        return (1 - probability) * factor * ageRisk * termYears;
    }

    @Override
    public double calculateValue() {
        return insuredAmount * interestRate * termYears;
    }
}
