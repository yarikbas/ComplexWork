// com/org/insurance/service/InsuranceCalculator.java
package com.org.insurance.service;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;

public class InsuranceCalculator {
    public double calculatePortfolioValue(Derivative d) {
        return d.getItems().stream().mapToDouble(Obligation::calculateValue).sum();
    }
    public double calculateTotalRisk(Derivative d) {
        return d.getItems().stream().mapToDouble(Obligation::calculateRisk).sum();
    }
    public double calculatePriceOfService(Obligation o) {
        return o.calculatePayout();
    }
}
