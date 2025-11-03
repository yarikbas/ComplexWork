package com.org.insurance.domain;

public class BusinessObligation extends Obligation {
    private String registrationNumber;
    private String industry;

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }
}
