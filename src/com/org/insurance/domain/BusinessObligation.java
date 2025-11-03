package com.org.insurance.domain;

public class BusinessObligation extends Obligation {
    private static final long serialVersionUID = 1L;

    private String businessType; // "retail","it","manufacturing", ...
    private int employees;       // >=0
    private int incidents;       // >=0 (кількість інцидентів у минулому)

    public BusinessObligation() {}

    public BusinessObligation(String name, double insuredAmount, double factor,
                              int period, double interestRate, double probability, double maxCost,
                              String businessType, int employees, int incidents) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.businessType = businessType;
        setEmployees(employees);
        setIncidents(incidents);
    }

    @Override public double calculatePayout() {
        double empK = 1.0 + Math.max(0, employees - 10) * 0.002; // трохи зростає з масштабом
        double raw  = getInsuredAmount() * getProbability() * empK;
        return Math.min(raw, getMaxCost());
    }

    @Override public double calculateRisk() {
        double indK = "manufacturing".equalsIgnoreCase(businessType) ? 1.2
                : "retail".equalsIgnoreCase(businessType)        ? 1.0
                : 0.9; // умовно для "it" та ін.
        double incK = 1.0 + incidents * 0.05;
        return (1.0 - getProbability()) * getFactor() * indK * incK;
    }

    @Override public double calculateValue() {
        return getInsuredAmount() * getInterestRate() * getPeriod();
    }

    public String getBusinessType() { return businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; }

    public int getEmployees() { return employees; }
    public final void setEmployees(int v) {
        if (v < 0) throw new IllegalArgumentException("employees must be >= 0");
        this.employees = v;
    }

    public int getIncidents() { return incidents; }
    public final void setIncidents(int v) {
        if (v < 0) throw new IllegalArgumentException("incidents must be >= 0");
        this.incidents = v;
    }
}
