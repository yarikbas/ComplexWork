package com.org.insurance.domain;

import java.io.Serializable;
import java.util.UUID;

public abstract class Obligation implements Serializable {
    private static final long serialVersionUID = 1L;

    private final UUID id = UUID.randomUUID();

    private String name;
    private double insuredAmount;
    private double factor;
    private int period;
    private double interestRate;
    private double probability;
    private double maxCost;

    protected Obligation() {}

    protected Obligation(String name, double insuredAmount, double factor,
                         int period, double interestRate, double probability, double maxCost) {
        this.name = name;
        setInsuredAmount(insuredAmount);
        setFactor(factor);
        setPeriod(period);
        setInterestRate(interestRate);
        setProbability(probability);
        setMaxCost(maxCost);
    }

    // ---- формули ----
    public abstract double calculatePayout();
    public abstract double calculateRisk();
    public abstract double calculateValue();

    // ---- рівність лише по id ----
    @Override public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id.equals(((Obligation) o).id);
    }
    @Override public final int hashCode() { return id.hashCode(); }

    // ---- гетери/сеттери з валідацією ----
    public UUID getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name == null ? "" : name.trim(); }

    public double getInsuredAmount() { return insuredAmount; }
    public final void setInsuredAmount(double v) {
        if (v < 0) throw new IllegalArgumentException("insuredAmount must be >= 0");
        this.insuredAmount = v;
    }

    public double getFactor() { return factor; }
    public final void setFactor(double v) {
        if (v <= 0) throw new IllegalArgumentException("factor must be > 0");
        this.factor = v;
    }

    public int getPeriod() { return period; }
    public final void setPeriod(int v) {
        if (v <= 0) throw new IllegalArgumentException("period must be > 0");
        this.period = v;
    }

    public double getInterestRate() { return interestRate; }
    public final void setInterestRate(double v) {
        if (v < 0) throw new IllegalArgumentException("interestRate must be >= 0");
        this.interestRate = v;
    }

    public double getProbability() { return probability; }
    public final void setProbability(double v) {
        if (v < 0 || v > 1) throw new IllegalArgumentException("probability must be in [0,1]");
        this.probability = v;
    }

    public double getMaxCost() { return maxCost; }
    public final void setMaxCost(double v) {
        if (v < 0) throw new IllegalArgumentException("maxCost must be >= 0");
        this.maxCost = v;
    }

    @Override public String toString() {
        return getClass().getSimpleName()+"{id="+id+", name='"+name+"'}";
    }
}
