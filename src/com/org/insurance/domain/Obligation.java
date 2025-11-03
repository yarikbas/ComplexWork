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

    // Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public double getInsuredAmount() { return insuredAmount; }
    public double getFactor() { return factor; }
    public int getPeriod() { return period; }
    public double getInterestRate() { return interestRate; }
    public double getProbability() { return probability; }
    public double getMaxCost() { return maxCost; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setInsuredAmount(double insuredAmount) { this.insuredAmount = insuredAmount; }
    public void setFactor(double factor) { this.factor = factor; }
    public void setPeriod(int period) { this.period = period; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
    public void setProbability(double probability) { this.probability = probability; }
    public void setMaxCost(double maxCost) { this.maxCost = maxCost; }
}
