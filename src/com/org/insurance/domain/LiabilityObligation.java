package com.org.insurance.domain;

public class LiabilityObligation extends Obligation {
    private String coverageType;
    private String jurisdiction;

    public String getCoverageType() { return coverageType; }
    public void setCoverageType(String coverageType) { this.coverageType = coverageType; }

    public String getJurisdiction() { return jurisdiction; }
    public void setJurisdiction(String jurisdiction) { this.jurisdiction = jurisdiction; }
}
