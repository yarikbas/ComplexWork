package com.org.insurance.domain;

import java.time.LocalDate;

public class TravelObligation extends Obligation {
    private String destinationCountry;
    private LocalDate tripStartDate;
    private LocalDate tripEndDate;

    protected TravelObligation(String name, double insuredAmount, double factor, int period, double interestRate, double probability, double maxCost) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
    }

    public String getDestinationCountry() { return destinationCountry; }
    public void setDestinationCountry(String destinationCountry) { this.destinationCountry = destinationCountry; }

    public LocalDate getTripStartDate() { return tripStartDate; }
    public void setTripStartDate(LocalDate tripStartDate) { this.tripStartDate = tripStartDate; }

    public LocalDate getTripEndDate() { return tripEndDate; }
    public void setTripEndDate(LocalDate tripEndDate) { this.tripEndDate = tripEndDate; }
}
