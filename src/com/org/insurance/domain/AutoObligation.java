package com.org.insurance.domain;

public class AutoObligation extends Obligation {
    private static final long serialVersionUID = 1L;

    private String vehicleType;
    private String driverClass;   // "A","B","C"
    private double bonusMalus;    // 0.8..1.2

    public AutoObligation() {}

    public AutoObligation(String name, double insuredAmount, double factor,
                          int period, double interestRate, double probability, double maxCost,
                          String vehicleType, String driverClass, double bonusMalus) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.vehicleType = vehicleType;
        this.driverClass = driverClass;
        setBonusMalus(bonusMalus);
    }

    @Override public double calculatePayout() {
        return getInsuredAmount() * getProbability() * bonusMalus;
    }
    @Override public double calculateRisk() {
        double k = "A".equalsIgnoreCase(driverClass) ? 0.8 :
                "B".equalsIgnoreCase(driverClass) ? 1.0 : 1.2;
        return (1.0 - getProbability()) * getFactor() * k;
    }
    @Override public double calculateValue() {
        return getInsuredAmount() * getInterestRate() * getPeriod() * bonusMalus;
    }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    public String getDriverClass() { return driverClass; }
    public void setDriverClass(String driverClass) { this.driverClass = driverClass; }

    public double getBonusMalus() { return bonusMalus; }
    public final void setBonusMalus(double v) {
        if (v <= 0) throw new IllegalArgumentException("bonusMalus must be > 0");
        this.bonusMalus = v;
    }
}
