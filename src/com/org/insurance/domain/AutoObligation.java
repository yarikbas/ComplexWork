package com.org.insurance.domain;

public class AutoObligation extends Obligation {
    private String vehicleVin;
    private String vehiclePlate;

    protected AutoObligation(String name, double insuredAmount, double factor, int period, double interestRate, double probability, double maxCost) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
    }

    public String getVehicleVin() { return vehicleVin; }
    public void setVehicleVin(String vehicleVin) { this.vehicleVin = vehicleVin; }

    public String getVehiclePlate() { return vehiclePlate; }
    public void setVehiclePlate(String vehiclePlate) { this.vehiclePlate = vehiclePlate; }
}
