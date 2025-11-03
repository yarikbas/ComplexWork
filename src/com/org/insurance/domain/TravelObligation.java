package com.org.insurance.domain;

public class TravelObligation extends Obligation {
    private static final long serialVersionUID = 1L;

    private String destination;     // країна/регіон
    private int days;               // >0
    private double destinationRisk; // >0 (0.8..1.5)

    public TravelObligation() {}

    public TravelObligation(String name, double insuredAmount, double factor,
                            int period, double interestRate, double probability, double maxCost,
                            String destination, int days, double destinationRisk) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.destination = destination;
        setDays(days);
        setDestinationRisk(destinationRisk);
    }

    @Override public double calculatePayout() {
        double tripK = Math.max(1.0, days / 7.0);
        double raw   = getInsuredAmount() * getProbability() * tripK;
        return Math.min(raw, getMaxCost());
    }

    @Override public double calculateRisk() {
        return (1.0 - getProbability()) * getFactor() * destinationRisk;
    }

    @Override public double calculateValue() {
        return getInsuredAmount() * getInterestRate() * Math.max(1, days / 7);
    }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public int getDays() { return days; }
    public final void setDays(int v) {
        if (v <= 0) throw new IllegalArgumentException("days must be > 0");
        this.days = v;
    }

    public double getDestinationRisk() { return destinationRisk; }
    public final void setDestinationRisk(double v) {
        if (v <= 0) throw new IllegalArgumentException("destinationRisk must be > 0");
        this.destinationRisk = v;
    }
}
