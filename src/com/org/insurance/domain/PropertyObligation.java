package com.org.insurance.domain;

public class PropertyObligation extends Obligation {
    private String propertyAddress;
    private String propertyType;

    public String getPropertyAddress() { return propertyAddress; }
    public void setPropertyAddress(String propertyAddress) { this.propertyAddress = propertyAddress; }

    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }
}
