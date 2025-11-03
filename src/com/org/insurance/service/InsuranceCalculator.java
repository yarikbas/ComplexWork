package com.org.insurance.service;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;

public class InsuranceCalculator {

    public double calculatePortfolioValue(Derivative d) {
        double sum = 0.0;
        for (int i = 0; i < d.getItems().size(); i++) {
            sum += d.getItems().get(i).calculateValue();
        }
        return sum;
    }

    public double calculateTotalRisk(Derivative d) {
        double sum = 0.0;
        for (int i = 0; i < d.getItems().size(); i++) {
            sum += d.getItems().get(i).calculateRisk();
        }
        return sum;
    }

    public double calculatePriceOfService(Obligation o) {
        return o.calculatePayout(); // базово
    }
}
