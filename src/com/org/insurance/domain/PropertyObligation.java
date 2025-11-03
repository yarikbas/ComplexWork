package com.org.insurance.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PropertyObligation extends Obligation {
    private String location;
    private double deductible;
    private String constructionType;

    public PropertyObligation(String name, double insuredAmount, double factor,
                              int period, double interestRate, double probability,
                              double maxCost, String location, double deductible, String constructionType) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.location = location;
        this.deductible = deductible;
        this.constructionType = constructionType;
    }

    @Override
    public double calculatePayout() {
        return (insuredAmount - deductible) * probability;
    }

    @Override
    public double calculateRisk() {
        double locationRisk = "high-risk".equals(location) ? 1.5 : 1.0;
        return (1 - probability) * factor * locationRisk;
    }

    @Override
    public double calculateValue() {
        return insuredAmount * interestRate * period;
    }
}