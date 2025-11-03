package com.org.insurance.domain;

public class LiabilityObligation extends Obligation {
    private String coverageType;
    private String jurisdiction;

    protected LiabilityObligation(String name, double insuredAmount, double factor, int period, double interestRate, double probability, double maxCost) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
    }

    public String getCoverageType() { return coverageType; }
    public void setCoverageType(String coverageType) { this.coverageType = coverageType; }

    public String getJurisdiction() { return jurisdiction; }
    public void setJurisdiction(String jurisdiction) { this.jurisdiction = jurisdiction; }
}
