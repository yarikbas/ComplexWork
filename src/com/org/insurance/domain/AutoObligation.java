package com.org.insurance.domain;

import java.util.Scanner;

public class AutoObligation extends Obligation {

    private String vehicleType;
    private String driverClass;
    private double bonusMalus;

    public AutoObligation(){
        Scanner in =  new Scanner(System.in);
        super(in);
        this.setSpecificFields(in);
    }

    public AutoObligation(String name, double insuredAmount, double factor,
                          int period, double interestRate, double probability, double maxCost,
                          String vehicleType, String driverClass, double bonusMalus) {
        super(name, insuredAmount, factor, period, interestRate, probability, maxCost);
        this.vehicleType = vehicleType;
        this.driverClass = driverClass;
        this.bonusMalus = bonusMalus;
    }

    @Override
    public void setSpecificFields(Scanner in) {
        System.out.print("vehicleType: ");
        String vt = in.nextLine().trim();
        if (!vt.isEmpty()) this.vehicleType = vt;

        System.out.print("driverClass: ");
        String dc = in.nextLine().trim();
        if (!dc.isEmpty()) this.driverClass = dc;

        System.out.print("bonusMalus: ");
        String bm = in.nextLine().trim();
        if (!bm.isEmpty()) {
            try { this.bonusMalus = Double.parseDouble(bm); } catch (NumberFormatException ignored) {}
        }
    }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getDriverClass() { return driverClass; }
    public void setDriverClass(String driverClass) { this.driverClass = driverClass; }

    public double getBonusMalus() { return bonusMalus; }
    public void setBonusMalus(double bonusMalus) { this.bonusMalus = bonusMalus; }
}
