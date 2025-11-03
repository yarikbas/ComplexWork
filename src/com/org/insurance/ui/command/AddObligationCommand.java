package com.org.insurance.ui.command;

import com.org.insurance.domain.*;
import com.org.insurance.ui.InsuranceMenu;
import com.org.insurance.ui.Inputs;

import java.util.Scanner;

public class AddObligationCommand implements Command {
    private final InsuranceMenu menu;
    public AddObligationCommand(InsuranceMenu menu) { this.menu = menu; }

    @Override public String getDescription() { return "Add obligation to selected derivative"; }

    @Override
    public void execute(Scanner sc) {
        Derivative d = menu.getSelected();
        if (d == null) { System.out.println("No selected derivative."); return; }

        System.out.println("Type: 1-Auto 2-Life 3-Health");
        int t = Inputs.nextInt(sc, ">", 1, 3);

        String name = Inputs.nextLine(sc, "name: ");
        double insured = Inputs.nextDouble(sc, "insuredAmount: ", 0.0, null);
        double factor  = Inputs.nextDouble(sc, "factor (>0): ", 0.0000001, null);
        int period     = Inputs.nextInt(sc, "period (>0): ", 1, null);
        double rate    = Inputs.nextDouble(sc, "interestRate (>=0): ", 0.0, null);
        double p       = Inputs.nextDouble(sc, "probability [0..1]: ", 0.0, 1.0);
        double maxCost = Inputs.nextDouble(sc, "maxCost (>=0): ", 0.0, null);

        Obligation o;
        if (t == 1) {
            String vt = Inputs.nextLine(sc, "vehicleType: ");
            String dc = Inputs.nextLine(sc, "driverClass (A/B/C): ");
            double bm = Inputs.nextDouble(sc, "bonusMalus (>0): ", 0.0000001, null);
            o = new AutoObligation(name, insured, factor, period, rate, p, maxCost, vt, dc, bm);
        } else if (t == 2) {
            int age  = Inputs.nextInt(sc, "age (>0): ", 1, null);
            int term = Inputs.nextInt(sc, "termYears (>0): ", 1, null);
            o = new LifeObligation(name, insured, factor, period, rate, p, maxCost, age, term);
        } else {
            String ct = Inputs.nextLine(sc, "coverageType: ");
            boolean pre = Inputs.nextBoolean(sc, "preExistingConditions");
            double lim = Inputs.nextDouble(sc, "annualLimit (>0): ", 0.0000001, null);
            o = new HealthObligation(name, insured, factor, period, rate, p, maxCost, ct, pre, lim);
        }

        d.add(o);
        System.out.println("Added: " + o.getName());
    }
}
