package com.org.insurance.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Obligation implements Serializable {
    @EqualsAndHashCode.Include
    protected final UUID id = UUID.randomUUID();

    protected String name;
    protected double insuredAmount;
    protected double factor;
    protected int period;
    protected double interestRate;
    protected double probability;
    protected double maxCost;

    public Obligation() {}
    public Obligation(String name, double insuredAmount, double factor,
                      int period, double interestRate, double probability, double maxCost) {
        this.name = name; this.insuredAmount = insuredAmount; this.factor = factor;
        this.period = period; this.interestRate = interestRate;
        this.probability = probability; this.maxCost = maxCost;
    }

    public abstract double calculatePayout();
    public abstract double calculateRisk();
    public abstract double calculateValue();
}
