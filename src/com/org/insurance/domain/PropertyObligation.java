package com.org.insurance.domain;

public class PropertyObligation extends Obligation {
    private static final long serialVersionUID = 1L;

    private String propertyType; // "house","apartment", ...
    private double regionRisk;   // >0 (наприклад 0.8..1.3)

    public PropertyObligation() {}

    public PropertyObligation(String name, double insuredAmount, double factor,
                              int period, double interestRate, double probability, double maxCost,
                              String propertyType, double regionRisk) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.propertyType = propertyType;
        setRegionRisk(regionRisk);
    }

    @Override public double calculatePayout() {
        return Math.min(getInsuredAmount() * getProbability(), getMaxCost());
    }

    @Override public double calculateRisk() {
        return (1.0 - getProbability()) * getFactor() * regionRisk;
    }

    @Override public double calculateValue() {
        return getInsuredAmount() * getInterestRate() * getPeriod();
    }

    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }

    public double getRegionRisk() { return regionRisk; }
    public final void setRegionRisk(double v) {
        if (v <= 0) throw new IllegalArgumentException("regionRisk must be > 0");
        this.regionRisk = v;
    }
}
