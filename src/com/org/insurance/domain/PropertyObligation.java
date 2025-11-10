package com.org.insurance.domain;

import java.util.Scanner;

public class PropertyObligation extends Obligation {
    private String propertyAddress;
    private String propertyType;

    public PropertyObligation(){
        Scanner in =  new Scanner(System.in);
        super(in);
        this.setSpecificFields(in);
    }

    @Override
    public String toString() {
        super.toString();
        return "PropertyObligation{" +
                "name=" + getName() +
                ", id=" + getId() +
                ", insuredAmount=" + getInsuredAmount() +
                ", factor=" + getFactor() +
                ", period=" + getPeriod() +
                ", interestRate=" + getInterestRate() +
                ", probability=" + getProbability() +
                ", maxCost=" + getMaxCost() +
                "propertyAddress='" + propertyAddress + '\'' +
                ", propertyType='" + propertyType + '\'' +
                '}';
    }

    public PropertyObligation(String name, double insuredAmount, double factor,
                              int period, double interestRate, double probability, double maxCost,
                              String propertyAddress, String propertyType) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.propertyAddress = propertyAddress;
        this.propertyType = propertyType;
    }

    @Override
    public void setSpecificFields(Scanner in) {
        System.out.print("Адреса нерухомості: ");
        String addr = in.nextLine().trim();
        if (!addr.isEmpty()) this.propertyAddress = addr;

        System.out.print("Тип нерухомості (квартира/будинок/офіс тощо): ");
        String type = in.nextLine().trim();
        if (!type.isEmpty()) this.propertyType = type;
    }

    public String getPropertyAddress() { return propertyAddress; }
    public void setPropertyAddress(String propertyAddress) { this.propertyAddress = propertyAddress; }

    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }
}
