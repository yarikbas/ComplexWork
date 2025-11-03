package com.org.insurance.domain;

public class BusinessObligation extends Obligation {
    private String registrationNumber;
    private String industry;

    protected BusinessObligation(String name, double insuredAmount, double factor, int period, double interestRate, double probability, double maxCost) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
    }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }
}
