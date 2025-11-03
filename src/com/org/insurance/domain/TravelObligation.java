package com.org.insurance.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TravelObligation extends Obligation {
    private int tripDays;
    private String destination;
    private String purpose;

    public TravelObligation(String name, double insuredAmount, double factor,
                            int period, double interestRate, double probability,
                            double maxCost, int tripDays, String destination, String purpose) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.tripDays = tripDays;
        this.destination = destination;
        this.purpose = purpose;
    }

    @Override
    public double calculatePayout() {
        return insuredAmount * probability * (tripDays / 30.0);
    }

    @Override
    public double calculateRisk() {
        return (1 - probability) * factor * tripDays;
    }

    @Override
    public double calculateValue() {
        return insuredAmount * interestRate * (tripDays / 365.0);
    }
}