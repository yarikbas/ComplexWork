package com.org.insurance.domain;

public class LiabilityObligation extends Obligation {
    private static final long serialVersionUID = 1L;

    private double liabilityLimit; // >0
    private double industryRisk;   // >0 (0.8..1.4, наприклад)

    public LiabilityObligation() {}

    public LiabilityObligation(String name, double insuredAmount, double factor,
                               int period, double interestRate, double probability, double maxCost,
                               double liabilityLimit, double industryRisk) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        setLiabilityLimit(liabilityLimit);
        setIndustryRisk(industryRisk);
    }

    @Override public double calculatePayout() {
        double raw = liabilityLimit * getProbability();
        return Math.min(raw, getMaxCost());
    }

    @Override public double calculateRisk() {
        return (1.0 - getProbability()) * getFactor() * industryRisk;
    }

    @Override public double calculateValue() {
        return getInsuredAmount() * getInterestRate() * getPeriod();
    }

    public double getLiabilityLimit() { return liabilityLimit; }
    public final void setLiabilityLimit(double v) {
        if (v <= 0) throw new IllegalArgumentException("liabilityLimit must be > 0");
        this.liabilityLimit = v;
    }

    public double getIndustryRisk() { return industryRisk; }
    public final void setIndustryRisk(double v) {
        if (v <= 0) throw new IllegalArgumentException("industryRisk must be > 0");
        this.industryRisk = v;
    }
}
