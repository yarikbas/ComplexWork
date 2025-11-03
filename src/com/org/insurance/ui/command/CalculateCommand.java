package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.service.InsuranceCalculator;
import com.org.insurance.ui.InsuranceMenu;

import java.util.Scanner;

public class CalculateCommand implements Command {
    private final InsuranceMenu menu;
    private final InsuranceCalculator calc = new InsuranceCalculator();

    public CalculateCommand(InsuranceMenu menu) { this.menu = menu; }

    @Override public String getDescription() { return "Calculate total value and risk of selected derivative"; }

    @Override
    public void execute(Scanner sc) {
        Derivative d = menu.getSelected();
        if (d == null) { System.out.println("No selected derivative."); return; }
        System.out.println("Total value = " + calc.calculatePortfolioValue(d));
        System.out.println("Total risk  = " + calc.calculateTotalRisk(d));
    }
}
