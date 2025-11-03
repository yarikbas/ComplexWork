package com.org.insurance.domain;

import java.time.LocalDate;

public class HealthObligation extends Obligation {
    private String insuredPersonId;
    private LocalDate dateOfBirth;

    public String getInsuredPersonId() { return insuredPersonId; }
    public void setInsuredPersonId(String insuredPersonId) { this.insuredPersonId = insuredPersonId; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
}
