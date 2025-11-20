package com.org.insurance.domain;

import java.util.List;

public final class InsuranceCalculator {

    public double calculatePortfolioValue(Derivative derivative) {
        if (derivative == null) {
            return 0.0;
        }

        List<Obligation> obligations = derivative.getObligations();
        if (obligations == null || obligations.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (int i = 0; i < obligations.size(); i++) {
            Obligation o = obligations.get(i);
            if (o == null) {
                continue;
            }
            total += calculatePriceOfService(o);
        }

        return total;
    }

    /**
     * Сумарний ризик портфеля:
     * тут беремо таку саму формулу, як у RiskComparator.riskScore:
     * risk = insuredAmount * factor * probability.
     * Портфельний ризик = сума цих ризиків по всіх облігаціях.
     */
    public double calculateTotalRisk(Derivative derivative) {
        if (derivative == null) {
            return 0.0;
        }

        List<Obligation> obligations = derivative.getObligations();
        if (obligations == null || obligations.isEmpty()) {
            return 0.0;
        }

        double totalRisk = 0.0;

        for (int i = 0; i < obligations.size(); i++) {
            Obligation o = obligations.get(i);
            if (o == null) {
                continue;
            }

            double insuredAmount = o.getInsuredAmount();
            double factor = o.getFactor();
            double probability = o.getProbability();

            double riskScore = insuredAmount * factor * probability;
            totalRisk += riskScore;
        }

        return totalRisk;
    }

    /**
     * Ціна сервісу (страхова премія) для однієї облігації.
     *
     * Ідея формули:
     * 1) expectedLoss = insuredAmount * probability * factor
     *    (математичне сподівання збитку з урахуванням коефіцієнта ризику).
     * 2) years = periodMonths / 12.0
     * 3) timeCoeff = 1 + interestRate * years
     *    (коефіцієнт з урахуванням ставки та строку).
     * 4) grossPremium = expectedLoss * timeCoeff
     * 5) Якщо є maxCost > 0 і премія вийшла більшою, ніж maxCost — обрізаємо до maxCost.
     * 6) Якщо раптом вийшло < 0 — повертаємо 0.
     */
    public double calculatePriceOfService(Obligation obligation) {
        if (obligation == null) {
            return 0.0;
        }

        double insuredAmount = obligation.getInsuredAmount();
        double factor = obligation.getFactor();
        double periodMonths = obligation.getPeriod();
        double interestRate = obligation.getInterestRate();
        double probability = obligation.getProbability();
        double maxCost = obligation.getMaxCost();

        if (insuredAmount <= 0.0 || probability <= 0.0 || factor <= 0.0) {
            return 0.0;
        }

        double expectedLoss = insuredAmount * probability * factor;

        double years = periodMonths / 12.0;
        if (years < 0.0) {
            years = 0.0;
        }

        double timeCoeff = 1.0 + interestRate * years;

        double grossPremium = expectedLoss * timeCoeff;

        if (maxCost > 0.0 && grossPremium > maxCost) {
            grossPremium = maxCost;
        }

        if (grossPremium < 0.0) {
            grossPremium = 0.0;
        }

        return grossPremium;
    }
}
