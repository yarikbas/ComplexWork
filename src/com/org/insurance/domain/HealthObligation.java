package com.org.insurance.domain;

import java.util.Scanner;

public class HealthObligation extends Obligation {

    private String coverageType;
    private boolean hasPreExistingConditions;
    private double annualLimit;

    public HealthObligation(){
        Scanner in =  new Scanner(System.in);
        super(in);
        this.setSpecificFields(in);
    }


    public HealthObligation(String name, double insuredAmount, double factor, int period,
                            double interestRate, double probability, double maxCost,
                            String coverageType, boolean hasPreExistingConditions, double annualLimit) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.coverageType = coverageType;
        this.hasPreExistingConditions = hasPreExistingConditions;
        this.annualLimit = annualLimit;
    }

    @Override
    public void setSpecificFields(Scanner in) {
        System.out.print("тип покриття (coverageType): ");
        String ct = in.nextLine().trim();
        if (!ct.isEmpty()) this.coverageType = ct;

        System.out.print("наявні хронічні/попередні стани? (true/false): ");
        String s = in.nextLine().trim().toLowerCase();
        if (!s.isEmpty()) this.hasPreExistingConditions = Boolean.parseBoolean(s);

        System.out.print("річний ліміт (annualLimit): ");
        String lim = in.nextLine().trim();
        if (!lim.isEmpty()) {
            try { this.annualLimit = Double.parseDouble(lim); } catch (NumberFormatException ignored) {}
        }
    }

    public String getcoverageType() { return coverageType; }
    public void setcoverageType(String coverageType) { this.coverageType = coverageType; }

    public boolean isHasPreExistingConditions() { return hasPreExistingConditions; }
    public void setHasPreExistingConditions(boolean hasPreExistingConditions) { this.hasPreExistingConditions = hasPreExistingConditions; }

    public double getAnnualLimit() { return annualLimit; }
    public void setAnnualLimit(double annualLimit) { this.annualLimit = annualLimit; }
}
