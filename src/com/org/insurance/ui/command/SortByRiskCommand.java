package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;
import com.org.insurance.ui.InsuranceMenu;

import java.util.List;
import java.util.Scanner;

public class SortByRiskCommand implements Command {
    private final InsuranceMenu menu;
    public SortByRiskCommand(InsuranceMenu menu) { this.menu = menu; }

    @Override public String getDescription() { return "Show obligations sorted by decreasing risk"; }

    @Override
    public void execute(Scanner sc) {
        Derivative d = menu.getSelected();
        if (d == null) { System.out.println("No selected derivative."); return; }
        List<Obligation> sorted = d.sortedByRiskDesc();
        for (int i = 0; i < sorted.size(); i++) {
            Obligation o = sorted.get(i);
            System.out.println(i + ": " + o.getName() + " | risk=" + o.calculateRisk());
        }
    }
}
