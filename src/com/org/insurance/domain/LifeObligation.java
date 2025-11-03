package com.org.insurance.domain;

public class LifeObligation extends Obligation {
    private static final long serialVersionUID = 1L;

    private int age;
    private int termYears;

    public LifeObligation() {}

    public LifeObligation(String name, double insuredAmount, double factor,
                          int period, double interestRate, double probability, double maxCost,
                          int age, int termYears) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        setAge(age);
        setTermYears(termYears);
    }

    @Override public double calculatePayout() {
        return getInsuredAmount() * getProbability();
    }
    @Override public double calculateRisk() {
        double k = (age <= 35) ? 0.8 : (age <= 55) ? 1.0 : 1.3;
        return (1.0 - getProbability()) * getFactor() * k;
    }
    @Override public double calculateValue() {
        return getInsuredAmount() * getInterestRate() * Math.max(1, termYears);
    }

    public int getAge() { return age; }
    public final void setAge(int v) {
        if (v <= 0) throw new IllegalArgumentException("age must be > 0");
        this.age = v;
    }
    public int getTermYears() { return termYears; }
    public final void setTermYears(int v) {
        if (v <= 0) throw new IllegalArgumentException("termYears must be > 0");
        this.termYears = v;
    }
}
