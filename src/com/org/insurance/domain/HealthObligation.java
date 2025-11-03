package com.org.insurance.domain;

public class HealthObligation extends Obligation {
    private static final long serialVersionUID = 1L;

    private String coverageType;                 // "basic"/"plus"/"premium"
    private boolean hasPreExistingConditions;
    private double annualLimit;

    public HealthObligation() {}

    public HealthObligation(String name, double insuredAmount, double factor,
                            int period, double interestRate, double probability, double maxCost,
                            String coverageType, boolean hasPreExistingConditions, double annualLimit) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.coverageType = coverageType;
        this.hasPreExistingConditions = hasPreExistingConditions;
        setAnnualLimit(annualLimit);
    }

    @Override public double calculatePayout() {
        double k = "premium".equalsIgnoreCase(coverageType) ? 1.2 :
                "plus".equalsIgnoreCase(coverageType)    ? 1.0 : 0.8;
        double payout = getInsuredAmount() * getProbability() * k;
        return Math.min(payout, annualLimit);
    }
    @Override public double calculateRisk() {
        double k = hasPreExistingConditions ? 1.3 : 1.0;
        return (1.0 - getProbability()) * getFactor() * k;
    }
    @Override public double calculateValue() {
        return getInsuredAmount() * getInterestRate() * getPeriod();
    }

    public String getCoverageType() { return coverageType; }
    public void setCoverageType(String coverageType) { this.coverageType = coverageType; }
    public boolean isHasPreExistingConditions() { return hasPreExistingConditions; }
    public void setHasPreExistingConditions(boolean v) { this.hasPreExistingConditions = v; }

    public double getAnnualLimit() { return annualLimit; }
    public final void setAnnualLimit(double v) {
        if (v <= 0) throw new IllegalArgumentException("annualLimit must be > 0");
        this.annualLimit = v;
    }
}
