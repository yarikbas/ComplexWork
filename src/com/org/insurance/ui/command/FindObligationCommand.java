package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;
import com.org.insurance.ui.InsuranceMenu;
import com.org.insurance.ui.Inputs;

import java.util.List;
import java.util.Scanner;

public class FindObligationCommand implements Command {
    private final InsuranceMenu menu;
    public FindObligationCommand(InsuranceMenu menu) { this.menu = menu; }

    @Override public String getDescription() { return "Find obligations by ranges (amount/risk/value; -1 = skip)"; }

    @Override
    public void execute(Scanner sc) {
        Derivative d = menu.getSelected();
        if (d == null) { System.out.println("No selected derivative."); return; }

        Double minA = Inputs.nextDoubleOrSkip(sc, "min insuredAmount");
        Double maxA = Inputs.nextDoubleOrSkip(sc, "max insuredAmount");
        Double minR = Inputs.nextDoubleOrSkip(sc, "min risk");
        Double maxR = Inputs.nextDoubleOrSkip(sc, "max risk");
        Double minV = Inputs.nextDoubleOrSkip(sc, "min value");
        Double maxV = Inputs.nextDoubleOrSkip(sc, "max value");

        List<Obligation> res = d.findByRanges(minA, maxA, minR, maxR, minV, maxV);
        System.out.println("Found: " + res.size());
        for (int i = 0; i < res.size(); i++) {
            Obligation o = res.get(i);
            System.out.println("- " + o.getName() + " | risk=" + o.calculateRisk() + " | value=" + o.calculateValue());
        }
    }
}
